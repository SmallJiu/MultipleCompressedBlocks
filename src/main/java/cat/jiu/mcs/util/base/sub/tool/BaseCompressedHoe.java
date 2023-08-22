package cat.jiu.mcs.util.base.sub.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import cat.jiu.core.types.StackCaches;
import cat.jiu.core.util.JiuUtils;
import cat.jiu.core.util.RegisterModel;
import cat.jiu.core.util.base.BaseItemTool;
import cat.jiu.mcs.MCS;
import cat.jiu.mcs.api.ICompressedStuff;
import cat.jiu.mcs.api.ITooltipString;
import cat.jiu.mcs.api.recipe.IToolRecipe;
import cat.jiu.mcs.config.Configs;
import cat.jiu.mcs.exception.NonToolException;
import cat.jiu.mcs.util.MCSUtil;
import cat.jiu.mcs.util.ModSubtypes;
import cat.jiu.mcs.util.init.MCSCreativeTab;
import cat.jiu.mcs.util.init.MCSItems;
import cat.jiu.mcs.util.init.MCSResources;
import cat.jiu.mcs.util.type.CustomStuffType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseCompressedHoe extends BaseItemTool.MetaHoe implements ICompressedStuff, IToolRecipe {
	public static BaseCompressedHoe register(String name, ItemStack baseItem, String ownerMod, CreativeTabs tab, ICompressedStuff craftMaterialStack, ICompressedStuff craftRodStack) {
		if(baseItem == null || baseItem.isEmpty()) return null;
		if(Loader.isModLoaded(ownerMod) || ownerMod.equals("custom")) {
			return new BaseCompressedHoe(name, baseItem, ownerMod, tab, craftMaterialStack, craftRodStack);
		}else {
			return null;
		}
	}
	
	public static BaseCompressedHoe register(String name, ItemStack baseItem, String ownerMod, CreativeTabs tab) {
		return register(name, baseItem, ownerMod, tab, null, null);
	}

	public static BaseCompressedHoe register(String name, ItemStack baseItem, String ownerMod) {
		return register(name, baseItem, ownerMod, MCSCreativeTab.TOOLS);
	}

	protected final ItemStack baseToolStack;
	protected final ItemHoe baseTool;
	protected final float baseAttackSpeed;
	protected final String ownerMod;
	protected final ICompressedStuff craftMaterialStack;
	protected final ICompressedStuff craftRodStack;
	public BaseCompressedHoe(String name, ItemStack baseTool, String ownerMod, CreativeTabs tab) {
		this(name, baseTool, ownerMod, tab, null, null);
	}
	public BaseCompressedHoe(String name, ItemStack baseTool, String ownerMod, CreativeTabs tab, ICompressedStuff craftMaterialStack) {
		this(name, baseTool, ownerMod, tab, craftMaterialStack, MCSItems.minecraft.normal.C_STICK_I);
	}
	public BaseCompressedHoe(String name, ItemStack baseTool, String ownerMod, CreativeTabs tab, ICompressedStuff craftMaterialStack, ICompressedStuff craftRodStack) {
		super(MCS.MODID, name, tab, true, getToolMaterial(baseTool), ModSubtypes.values());
		this.baseToolStack = baseTool;
		if(baseTool.getItem() instanceof ItemHoe) {
			this.baseTool = (ItemHoe) baseTool.getItem();
		}else {
			this.baseTool = null;
			throw new NonToolException(baseTool, "Hoe");
		}
		this.ownerMod = ownerMod;
		this.baseAttackSpeed = this.baseTool.speed - 4.0F;
		this.setMaxMetadata(16);
		this.craftMaterialStack = craftMaterialStack;
		this.craftRodStack = craftRodStack;

		if(!ownerMod.equals("custom")) {
			MCSResources.ITEMS.add(this);
			MCSResources.STUFF_NAME.add(name);
			MCSResources.putCompressedStuff(this.baseToolStack, this);
		}
		if(name.equalsIgnoreCase(ownerMod)) {
			throw new RuntimeException("name must not be owner mod. Name: " + name + ", OwnerMod: " + ownerMod);
		}else if(name.equalsIgnoreCase(baseTool.getItem().getRegistryName().getResourceDomain())) {
			throw new RuntimeException("name must not be owner mod. Name: " + name + ", OwnerMod: " + baseTool.getItem().getRegistryName().getResourceDomain());
		}
	}
	public BaseCompressedHoe(String name, ItemStack baseTool, ICompressedStuff craftMaterialStack, ICompressedStuff craftRodStack) {
		this(name, baseTool, baseTool.getItem().getRegistryName().getResourceDomain(), MCSCreativeTab.TOOLS, craftMaterialStack, craftRodStack);
	}
	public BaseCompressedHoe(String name, ItemStack baseTool, ICompressedStuff craftMaterialStack) {
		this(name, baseTool, baseTool.getItem().getRegistryName().getResourceDomain(), MCSCreativeTab.TOOLS, craftMaterialStack);
	}
	public BaseCompressedHoe(String name, ItemStack baseTool) {
		this(name, baseTool, baseTool.getItem().getRegistryName().getResourceDomain(), MCSCreativeTab.TOOLS);
	}

	private static ToolMaterial getToolMaterial(ItemStack baseItem) {
		if(baseItem.getItem() instanceof ItemHoe) {
			return ((ItemHoe) baseItem.getItem()).toolMaterial;
		}
		return ToolMaterial.WOOD;
	}

	protected String unCompressedName;
	@Override
	public String getUnCompressedName() {
		if(this.unCompressedName==null) {
			String[] unNames = JiuUtils.other.custemSplitString(this.name, "_");
			StringBuffer i = new StringBuffer();
			for(String s : unNames) {
				if(!"compressed".equals(s)) {
					i.append(JiuUtils.other.upperFirst(s));
				}
			}
			this.unCompressedName = i.toString();
		}
		
		return this.unCompressedName;
	}

	public Map<Integer, Integer> EnchantabilityLevelMap = Maps.newHashMap();

	public BaseCompressedHoe setEnchantabilityLevel(Map<Integer, Integer> EnchantabilityLevelMap) {
		this.EnchantabilityLevelMap = EnchantabilityLevelMap;
		return this;
	}

	@Override
	public int getItemEnchantability(ItemStack stack) {
		if(!this.EnchantabilityLevelMap.isEmpty() && this.EnchantabilityLevelMap.containsKey(stack.getMetadata())) {
			return this.EnchantabilityLevelMap.get(stack.getMetadata());
		}
		int enchantability = this.baseTool.getItemEnchantability(this.baseToolStack);
		return (int) (enchantability + (enchantability * ((stack.getMetadata() + 1) * 0.29394)));
	}

	public Map<Integer, ItemStack> RepairableMap = Maps.newHashMap();

	public BaseCompressedHoe setRepairableMap(Map<Integer, ItemStack> RepairableMap) {
		this.RepairableMap = RepairableMap;
		return this;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		boolean lag = false;
		if(!this.RepairableMap.isEmpty() && this.RepairableMap.containsKey(toRepair.getMetadata())) {
			lag = JiuUtils.item.equalsStack(this.RepairableMap.get(toRepair.getMetadata()), repair);
		}
		return this.baseTool.getIsRepairable(this.baseToolStack, repair) || lag;
	}

	public Map<Integer, Float> DestroySpeedMap = Maps.newHashMap();

	public BaseCompressedHoe setDestroySpeed(Map<Integer, Float> DestroySpeedMap) {
		this.DestroySpeedMap = DestroySpeedMap;
		return this;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if(!this.DestroySpeedMap.isEmpty() && this.DestroySpeedMap.containsKey(stack.getMetadata())) {
			return this.DestroySpeedMap.get(stack.getMetadata());
		}
		return this.baseTool.getDestroySpeed(this.baseToolStack, state);
	}

	public Map<Integer, Integer> MaxDamageMap = Maps.newHashMap();

	public BaseCompressedHoe setMaxDamage(Map<Integer, Integer> MaxDamageMap) {
		this.MaxDamageMap = MaxDamageMap;
		return this;
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		if(!this.MaxDamageMap.isEmpty() && this.MaxDamageMap.containsKey(stack.getMetadata())) {
			return this.MaxDamageMap.get(stack.getMetadata());
		}
		return (int) MCSUtil.item.getMetaValue(this.baseToolStack.getMaxDamage(), stack.getMetadata());
	}

	Map<Integer, CustomStuffType.ToolModifiersType> AttributeModifierMap = Maps.newHashMap();
	public BaseCompressedHoe setAttributeModifierMap(Map<Integer, CustomStuffType.ToolModifiersType> attributeModifierMap) {
		this.AttributeModifierMap = attributeModifierMap;
		return this;
	}

	public BaseCompressedHoe addAttributeModifierMap(int meta, double speed, double damage) {
		this.AttributeModifierMap.put(meta, new CustomStuffType.ToolModifiersType(speed, damage));
		return this;
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = HashMultimap.create();

		if(slot == EntityEquipmentSlot.MAINHAND) {
			if(!this.AttributeModifierMap.isEmpty() && this.AttributeModifierMap.containsKey(stack.getMetadata())) {
				CustomStuffType.ToolModifiersType type = this.AttributeModifierMap.get(stack.getMetadata());
				multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", type.damage, 0));
				multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", type.speed, 0));
			}else {
				return this.baseTool.getAttributeModifiers(slot, this.baseToolStack);
			}
		}
		return multimap;
	}
	
	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockState) {
		return this.baseTool.getHarvestLevel(this.baseToolStack, toolClass, player, blockState);
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return this.baseTool.canHarvestBlock(state, this.baseToolStack);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public IRarity getForgeRarity(ItemStack stack) {
		return this.baseToolStack.getItem().getForgeRarity(this.baseToolStack);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return MCSUtil.info.getStuffDisplayName(this, stack.getMetadata());
	}

	private Map<Integer, Boolean> HasEffectMap = Maps.newHashMap();

	public BaseCompressedHoe setHasEffectMap(Map<Integer, Boolean> map) {
		this.HasEffectMap = map;
		return this;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasEffect(ItemStack stack) {
		if(!this.HasEffectMap.isEmpty()) {
			if(this.HasEffectMap.containsKey(stack.getMetadata())) {
				return this.HasEffectMap.get(stack.getMetadata());
			}
		}
		if(this.baseTool != null) {
			return this.baseTool.hasEffect(this.baseToolStack);
		}
		return super.hasEffect(stack);
	}

	private List<TextComponentTranslation> shiftInfos = new ArrayList<TextComponentTranslation>();

	public BaseCompressedHoe addCustemShiftInformation(TextComponentTranslation... custemInfo) {
		for(int i = 0; i < custemInfo.length; ++i) {
			shiftInfos.add(custemInfo[i]);
		}
		return this;
	}

	public BaseCompressedHoe addCustemShiftInformation(List<TextComponentTranslation> infos) {
		this.shiftInfos = infos;
		return this;
	}

	private Map<Integer, List<TextComponentTranslation>> metaShiftInfos = Maps.newHashMap();

	public BaseCompressedHoe addCustemShiftInformation(Map<Integer, List<TextComponentTranslation>> infos) {
		this.metaShiftInfos = infos;
		return this;
	}

	private List<TextComponentTranslation> infos = new ArrayList<TextComponentTranslation>();

	public BaseCompressedHoe addCustemInformation(TextComponentTranslation... custemInfo) {
		for(int i = 0; i < custemInfo.length; ++i) {
			infos.add(custemInfo[i]);
		}
		return this;
	}

	public BaseCompressedHoe addCustemInformation(List<TextComponentTranslation> infos) {
		this.infos = infos;
		return this;
	}

	private Map<Integer, List<TextComponentTranslation>> metaInfos = Maps.newHashMap();

	public BaseCompressedHoe addCustemInformation(Map<Integer, List<TextComponentTranslation>> infos) {
		this.metaInfos = infos;
		return this;
	}

	ItemStack infoStack = null;

	public BaseCompressedHoe setInfoStack(ItemStack stack) {
		this.infoStack = stack;
		return this;
	}

	private Map<Integer, ItemStack> infoStacks = Maps.newHashMap();

	public BaseCompressedHoe setInfoStack(Map<Integer, ItemStack> infoStacks) {
		this.infoStacks = infoStacks;
		return this;
	}
	
	private List<ITooltipString> infoHandler;
	public BaseCompressedHoe addInfoHandler(ITooltipString handler) {
		if(this.infoHandler==null) this.infoHandler = Lists.newArrayList();
		this.infoHandler.add(handler);
		return this;
	}
	
	protected boolean canShowBaseStackInfo = true;
	public BaseCompressedHoe setCanShowBaseStackInfo(boolean canShow) {
		this.canShowBaseStackInfo = canShow;
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		int meta = stack.getMetadata();
		if(this.canShowBaseStackInfo && !MCSUtil.info.addInfoStackInfo(meta, this.infoStack, world, tooltip, advanced, infoStacks)) {
			this.getUnCompressedStack().getItem().addInformation(getUnCompressedStack(), world, tooltip, advanced);
		}
		MCSUtil.info.addCompressedInfo(meta, tooltip, this.getUnCompressedItemLocalizedName(), this);

		if(Configs.Tooltip_Information.show_owner_mod) {
			tooltip.add(I18n.format("info.mcs.owner_mod") + " : " + TextFormatting.AQUA.toString() + this.getOwnerMod());
		}

		if(MCS.dev()) {
			tooltip.add("最大耐久: " + this.getMaxDamage(stack));
		}

		MCSUtil.info.addMetaInfo(meta, tooltip, this.infos, this.metaInfos);
		MCSUtil.info.addShiftInfo(meta, tooltip, this.shiftInfos, this.metaShiftInfos);
		MCSUtil.info.addHandlerString(tooltip, this.infoHandler, stack, world, advanced);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		MCSUtil.item.getSubItems(this, tab, items);
	}

	@Override
	public void getItemModel(RegisterModel util) {
		for(ModSubtypes type : ModSubtypes.values()) {
			int meta = type.getMeta();
			util.registerItemModel(this, meta, this.ownerMod + "/item/tools/hoe/" + this.name, this.name + "." + meta);
		}
		util.registerItemModel(this, (ModSubtypes.INFINITY), this.ownerMod + "/item/tools/hoe/" + this.name, this.name + "." + (ModSubtypes.INFINITY));
	}
	
	@Override
	public void setDamage(ItemStack stack, int damage) {
		if(stack.getMetadata() < ModSubtypes.INFINITY) {
			super.setDamage(stack, damage);
		}
	}
	
	@Override
	public void damageItem(ItemStack stack, int amount, EntityLivingBase entity) {
		if(stack.getMetadata() < ModSubtypes.INFINITY) {
			super.damageItem(stack, amount, entity);
		}
	}

	public String getOwnerMod() {
		return this.ownerMod;
	}

	public final Item getUnCompressedItem() {
		return this.baseToolStack.getItem();
	}

	public final ItemStack getUnCompressedStack() {
		return this.baseToolStack;
	}

	private boolean makeRecipe = true;

	public BaseCompressedHoe setMakeDefaultStackRecipe(boolean makeRecipe) {
		this.makeRecipe = makeRecipe;
		return this;
	}

	public boolean canMakeDefaultStackRecipe() {
		return this.makeRecipe;
	}

	@SideOnly(Side.CLIENT)
	public final String getUnCompressedItemLocalizedName() {
		return this.baseToolStack.getDisplayName();
	}
	
	private final StackCaches type = new StackCaches(this, ModSubtypes.MAX);
	@Override
	public StackCaches getLevel() {
		return this.type;
	}
	@Override
	public boolean canCreateRecipe(int meta) {
		return this.craftMaterialStack!=null && this.craftRodStack!=null;
	}
	@Override
	public ItemStack getMaterial(int meta) {
		if(this.craftMaterialStack.isBlock() && meta > 15) {
			meta = 15;
		}
		if(this.craftMaterialStack.isBlock() && (this.craftMaterialStack.isHas() || this.craftMaterialStack.getAsCompressedBlock().baseRecipeHasItem())) {
			if(meta <= 0) {
				return this.craftMaterialStack.getUnCompressedStack();
			}
			return this.craftMaterialStack.getStack(meta-1);
		}
		return this.craftMaterialStack.getStack(meta);
	}
	@Override
	public ItemStack getRod(int meta) {
		if(this.craftRodStack.isBlock() && meta > 15) {
			meta = 15;
		}
		if(this.craftRodStack.isBlock() && (this.craftRodStack.isHas() || this.craftRodStack.getAsCompressedBlock().baseRecipeHasItem())) {
			if(meta <= 0) {
				return this.craftRodStack.getUnCompressedStack();
			}
			return this.craftRodStack.getStack(meta-1);
		}
		return this.craftRodStack.getStack(meta);
	}
}
