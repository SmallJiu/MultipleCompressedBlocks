//Deobfuscated with https://github.com/PetoPetko/Minecraft-Deobfuscator3000 using mappings "1.12 stable mappings"!

package cat.jiu.mcs.util.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brandon3055.draconicevolution.DEFeatures;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import cat.jiu.mcs.MCS;
import cat.jiu.mcs.blocks.BlockCompressor;
import cat.jiu.mcs.blocks.BlockCreativeEnergy;
import cat.jiu.mcs.blocks.BlockTest;
import cat.jiu.mcs.blocks.compressed.*;
import cat.jiu.mcs.config.Configs;
import cat.jiu.mcs.exception.JsonException;
import cat.jiu.mcs.exception.NonItemException;
import cat.jiu.mcs.exception.UnknownTypeException;
import cat.jiu.core.util.JiuUtils;
import cat.jiu.mcs.util.base.BaseBlock;
import cat.jiu.mcs.util.base.BaseBlockNormal;
import cat.jiu.mcs.util.base.BaseBlockSub;
import cat.jiu.mcs.util.base.BaseBlockSub.HarvestType;
import cat.jiu.mcs.util.type.ChangeBlockType;
import cat.jiu.mcs.util.type.CustomType;

import cofh.thermalfoundation.init.TFBlocks;

import moze_intel.projecte.gameObjs.ObjHandler;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.common.Loader;

import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import vazkii.botania.common.block.ModFluffBlocks;

@SuppressWarnings("static-access")
public class MCSBlocks {
	public static final Map<String, BaseBlock> BLOCKS_MAP = new HashMap<String, BaseBlock>();
	public static final Map<String, BaseBlockSub> SUB_BLOCKS_MAP = new HashMap<String, BaseBlockSub>();
	public static final Map<String, BaseBlockNormal> NORMAL_BLOCKS_MAP = new HashMap<String, BaseBlockNormal>();
	
	public static final List<BaseBlock> BLOCKS = new ArrayList<BaseBlock>();
	public static final List<cat.jiu.core.util.base.BaseBlock.Base> BLOCKS0 = new ArrayList<cat.jiu.core.util.base.BaseBlock.Base>();
	public static final List<String> BLOCKS_NAME = new ArrayList<String>();
	public static final List<BaseBlockSub> SUB_BLOCKS = new ArrayList<BaseBlockSub>();
	public static final List<String> SUB_BLOCKS_NAME = new ArrayList<String>();
	public static final List<BaseBlockNormal> NORMAL_BLOCKS = new ArrayList<BaseBlockNormal>();
	
	public static BlockCompressor COMPRESSOR = new BlockCompressor();
	public static BlockCreativeEnergy CREATIVE_ENERGY = new BlockCreativeEnergy();
	public static final BaseBlockSub C_CREATIVE_ENERGY_B	= new CompressedCreativeEnergy("compressed_creative_energy", new ItemStack(CREATIVE_ENERGY));
	public static BaseBlockNormal TEST_BLOCK = null;
	static {
		if(MCS.instance.test_model) {
			TEST_BLOCK = new BlockTest().setBlockModelResourceLocation("mcs/block/test", "test_block");
		}
	}
	
	public static final MinecraftBlock minecraft = new MinecraftBlock();
	public static ThermalFoundationBlock thermal_foundation = null;
	public static EnderIOBlock enderio = null;
	public static ProjectEBlock projecte = null;
	public static DraconicEvolutionBlock draconic_evolution;
	public static EnvironmentalTechBlock environmental_tech = null;
	public static AvaritiaBlock avaritia = null;
	public static TconstructBlock tconstruct = null;
	public static BotaniaBlock botania = null;
	static {
		if(Configs.Custom.Enable_Mod_Stuff) {
			try {
				thermal_foundation = Configs.Custom.ModStuff.ThermalFoundation ? new ThermalFoundationBlock() : null;
				draconic_evolution = Configs.Custom.ModStuff.DraconicEvolution ? new DraconicEvolutionBlock() : null;
				avaritia = Configs.Custom.ModStuff.Avaritia ? new AvaritiaBlock() : null;
				
				if(Configs.Custom.Enable_Test_Stuff) {
					enderio = Configs.Custom.ModStuff.EnderIO ? new EnderIOBlock() : null;
					projecte = Configs.Custom.ModStuff.ProjectE ? new ProjectEBlock() : null;
					environmental_tech = Configs.Custom.ModStuff.EnvironmentalTech ? new EnvironmentalTechBlock() : null;
					tconstruct = Configs.Custom.ModStuff.Tconstruct ? new TconstructBlock() : null;
					botania = Configs.Custom.ModStuff.Botania ? new BotaniaBlock() : null;
				}
			} catch (Exception e) {
				MCS.instance.log.error("Has a error, this is message: ");
				MCS.instance.log.error(e.getMessage());
			}
		}
	}
	
	public MCSBlocks() {
		
	}
	
	public static final void registerOreDict() {
		for(BaseBlockSub block : SUB_BLOCKS) {
			JiuUtils.item.registerCompressedOre(block.getUnCompressedName(), block, block.isHasBlock());
		}
	}
	
	public static class MinecraftBlock{
		public final Normal normal = new Normal();
		public final Has has = new Has();
		
		public class Normal {
			public final BaseBlockSub C_BONE_B	 			= new BaseBlockSub("compressed_bone_block", new ItemStack(Blocks.BONE_BLOCK));
			public final BaseBlockSub C_DIAMOND_B 			= new BaseBlockSub("compressed_diamond_block", new ItemStack(Blocks.DIAMOND_BLOCK));
			public final BaseBlockSub C_DIRT_B 				= new BaseBlockSub("compressed_dirt_block",new ItemStack(Blocks.DIRT));
			public final BaseBlockSub C_EMERALD_B 			= new BaseBlockSub("compressed_emerald_block", new ItemStack(Blocks.EMERALD_BLOCK));
			public final BaseBlockSub C_GLASS_B 			= new BaseBlockSub("compressed_glass_block", new ItemStack(Blocks.GLASS));
			public final BaseBlockSub C_GLOW_STONE_B 		= new BaseBlockSub("compressed_glowstone_block", new ItemStack(Blocks.GLOWSTONE));
			public final BaseBlockSub C_GOLD_B 				= new BaseBlockSub("compressed_gold_block", new ItemStack(Blocks.GOLD_BLOCK));
			public final BaseBlockSub C_GRAVEL_B 			= new BaseBlockSub("compressed_gravel_block", new ItemStack(Blocks.GRAVEL));
			public final BaseBlockSub C_ICE_B 				= new BaseBlockSub("compressed_ice_block", new ItemStack(Blocks.ICE)).setIsTransparentCube();
			public final BaseBlockSub C_IRON_B 				= new BaseBlockSub("compressed_iron_block", new ItemStack(Blocks.IRON_BLOCK));
			public final BaseBlockSub C_MELON_B 			= new BaseBlockSub("compressed_melon_block", new ItemStack(Blocks.MELON_BLOCK));
			public final BaseBlockSub C_MAGMA_B 			= new BaseBlockSub("compressed_magma_block", new ItemStack(Blocks.MAGMA));
			public final BaseBlockSub C_NETHERRACK_B 		= new BaseBlockSub("compressed_netherrack_block", new ItemStack(Blocks.NETHERRACK));
			public final BaseBlockSub C_PUMPKIN_B 			= new BaseBlockSub("compressed_pumpkin_block", new ItemStack(Blocks.PUMPKIN));
			public final BaseBlockSub C_RED_STONE_B 		= new BaseBlockSub("compressed_redstone_block", new ItemStack(Blocks.REDSTONE_BLOCK));
			public final BaseBlockSub C_SAND_B 				= new BaseBlockSub("compressed_sand_block", new ItemStack(Blocks.SAND));
			public final BaseBlockSub C_SNOW_B 				= new BaseBlockSub("compressed_snow_block", new ItemStack(Blocks.SNOW));
			public final BaseBlockSub C_STONE_B 			= new BaseBlockSub("compressed_stone_block", new ItemStack(Blocks.STONE));
			public final BaseBlockSub C_WOOL_B 				= new BaseBlockSub("compressed_wool_block", new ItemStack(Blocks.WOOL));
			public final BaseBlockSub C_QUARTZ_BLOCK_B 		= new BaseBlockSub("compressed_quartz_block", new ItemStack(Blocks.QUARTZ_BLOCK));
			public final BaseBlockSub C_RED_MUSHROOM_B 		= new BaseBlockSub("compressed_red_mushroom_block", new ItemStack(Blocks.RED_MUSHROOM));
			public final BaseBlockSub C_BROWN_MUSHROOM_B 	= new BaseBlockSub("compressed_brown_mushroom_block", new ItemStack(Blocks.BROWN_MUSHROOM));
			public final BaseBlockSub C_PRISMARINE_B	 	= new BaseBlockSub("compressed_prismarine_block", new ItemStack(Blocks.PRISMARINE));
			public final BaseBlockSub C_BRICK_BLOCK_B 		= new BaseBlockSub("compressed_brick_block", new ItemStack(Blocks.BRICK_BLOCK));
			public final BaseBlockSub C_SOUL_SAND_B 		= new BaseBlockSub("compressed_soul_sand_block", new ItemStack(Blocks.SOUL_SAND));
			public final BaseBlockSub C_STONE_BRICK_B 		= new BaseBlockSub("compressed_stone_brick_block", new ItemStack(Blocks.STONEBRICK));
			public final BaseBlockSub C_CLAY_B 				= new BaseBlockSub("compressed_clay_block", new ItemStack(Blocks.CLAY));
			public final BaseBlockSub C_PURPUR_BLOCK_B 		= new BaseBlockSub("compressed_purpur_block", new ItemStack(Blocks.PURPUR_BLOCK));
			public final BaseBlockSub C_CONCRETE_POWDER_B 	= new BaseBlockSub("compressed_concrete_powder_block", new ItemStack(Blocks.CONCRETE_POWDER));
			public final BaseBlockSub C_CONCRETE_B 			= new BaseBlockSub("compressed_concrete_block", new ItemStack(Blocks.CONCRETE));
			public final BaseBlockSub C_HARDENED_CLAY_B 	= new BaseBlockSub("compressed_hardened_clay_block", new ItemStack(Blocks.HARDENED_CLAY));
			public final BaseBlockSub C_HAY_B 				= new BaseBlockSub("compressed_hay_block", new ItemStack(Blocks.HAY_BLOCK));
			public final BaseBlockSub C_END_STONE_B 		= new BaseBlockSub("compressed_end_stone_block", new ItemStack(Blocks.END_STONE));
			public final BaseBlockSub C_NETHER_BRICK_B 		= new BaseBlockSub("compressed_nether_brick_block", new ItemStack(Blocks.NETHER_BRICK));
			public final BaseBlockSub C_NETHER_WART_B		= new BaseBlockSub("compressed_nether_wart", new ItemStack(Blocks.NETHER_WART_BLOCK));
			public final BaseBlockSub C_SPONGE_B 			= new BaseBlockSub("compressed_sponge_block", new ItemStack(Blocks.SPONGE));
			public final BaseBlockSub C_PLANKS_B 			= new BaseBlockSub("compressed_planks_block", new ItemStack(Blocks.PLANKS));
			public final BaseBlockSub C_LOG_B 				= new BaseBlockSub("compressed_log_block", new ItemStack(Blocks.LOG));
			public final BaseBlockSub C_OBSIDIAN_B 			= new BaseBlockSub("compressed_obsidian_block", new ItemStack(Blocks.OBSIDIAN));
			public final BaseBlockSub C_LAPIS_B 			= new BaseBlockSub("compressed_lapis_block", new ItemStack(Blocks.LAPIS_BLOCK));
			
			public final BaseBlockSub C_BEDROCK_B	 		= new BaseBlockSub("compressed_bedrock_block", new ItemStack(Blocks.BEDROCK))
					.addChangeBlock(7, new int[] {0, 0, 30}, true, new ItemStack(Blocks.COMMAND_BLOCK));
			public final BaseBlockSub C_COAL_B 	 			= new BaseBlockSub("compressed_coal_block",new ItemStack(Blocks.COAL_BLOCK))
					.addChangeBlock(7, new int[] {0, 0, 10}, true, new ItemStack(C_DIAMOND_B, 1, 6));
			public final BaseBlockSub C_COBBLE_STONE_B 		= new BaseBlockSub("compressed_cobblestone_block", new ItemStack(Blocks.COBBLESTONE))
					.addChangeBlock(15, new int[] {0, 20, 0}, true, new ItemStack(C_BEDROCK_B, 1, 6));
			
			public final BaseBlockSub C_SLIME_BLOCK_B 		= new CompressedSlimeBlock("compressed_slime_block", new ItemStack(Blocks.SLIME_BLOCK));
			public final BaseBlockSub C_TNT_B 				= new CompressedTNT("compressed_tnt_block", new ItemStack(Blocks.TNT));
		}
		
		public class Has {
			public final BaseBlockSub C_NETHER_STAR_B		= new BaseBlockSub("compressed_nether_star", new ItemStack(Items.NETHER_STAR));
			public final BaseBlockSub C_CHAR_COAL_B 		= new BaseBlockSub("compressed_charcoal", new ItemStack(Items.COAL, 1, 1));
			public final BaseBlockSub C_BLAZE_B 			= new BaseBlockSub("compressed_blaze", new ItemStack(Items.BLAZE_ROD));
			public final BaseBlockSub C_CHORUS_FRUIT_B 		= new BaseBlockSub("compressed_chorus_fruit", new ItemStack(Items.CHORUS_FRUIT));
			public final BaseBlockSub C_ENDER_PEARL_B 		= new BaseBlockSub("compressed_ender_pearl", new ItemStack(Items.ENDER_PEARL));
			public final BaseBlockSub C_FLINT_B 			= new BaseBlockSub("compressed_flint", new ItemStack(Items.FLINT));
			public final BaseBlockSub C_GHAST_TEAR_B 		= new BaseBlockSub("compressed_ghast_tear", new ItemStack(Items.GHAST_TEAR));
			
		}
	}
	
	public static class ThermalFoundationBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		
		public static class Normal {
			public static BaseBlockSub C_ROCKWOOL_B 		= null;
			public static BaseBlockSub C_HARDENED_GLASS_B 	= null;
			public static BaseBlockSub C_FUEL_COKE_B 		= null;
			
			public static BaseBlockSub C_ALUMINUM_B 		= null;
			public static BaseBlockSub C_COPPER_B 			= null;
			public static BaseBlockSub C_IRIDIUM_B 			= null;
			public static BaseBlockSub C_LEAD_B 			= null;
			public static BaseBlockSub C_MITHRIL_B 			= null;
			public static BaseBlockSub C_NICKEL_B 			= null;
			public static BaseBlockSub C_PLATINUM_B 		= null;
			public static BaseBlockSub C_SILVER_B 			= null;
			public static BaseBlockSub C_TIN_B 				= null;
			
			public static BaseBlockSub C_BRONZE_B 			= null;
			public static BaseBlockSub C_CONSTANTAN_B 		= null;
			public static BaseBlockSub C_ELECTRUM_B 		= null;
			public static BaseBlockSub C_ENDERIUM_B 		= null;
			public static BaseBlockSub C_INVAR_B 			= null;
			public static BaseBlockSub C_LUMIUM_B 			= null;
			public static BaseBlockSub C_SIGNALUM_B 		= null;
			public static BaseBlockSub C_STEEL_B 			= null;
			
			static {
				try {
					C_ROCKWOOL_B	= register("compressed_rockwool_block", TFBlocks.blockRockwool.rockwoolWhite);
				C_HARDENED_GLASS_B	= ((BaseBlockSub) register("compressed_hardened_glass_block", TFBlocks.blockGlass.glassLead).canUseWrenchBreak(true)).setIsTransparentCube();
					C_FUEL_COKE_B	= register("compressed_fuel_coke_block", TFBlocks.blockStorageResource.blockCoke);
					
					C_ALUMINUM_B	= register("compressed_aluminum_block", TFBlocks.blockStorage.blockAluminum);
					C_COPPER_B		= register("compressed_copper_block", TFBlocks.blockStorage.blockCopper);
					C_IRIDIUM_B		= register("compressed_iridium_block", TFBlocks.blockStorage.blockIridium);
					C_LEAD_B		= register("compressed_lead_block", TFBlocks.blockStorage.blockLead);
					C_MITHRIL_B		= register("compressed_mithril_block", TFBlocks.blockStorage.blockMithril);
					C_NICKEL_B		= register("compressed_nickel_block", TFBlocks.blockStorage.blockNickel);
					C_PLATINUM_B	= register("compressed_platinum_block", TFBlocks.blockStorage.blockPlatinum);
					C_SILVER_B		= register("compressed_silver_block", TFBlocks.blockStorage.blockSilver);
					C_TIN_B			= register("compressed_tin_block", TFBlocks.blockStorage.blockTin);
					
					C_BRONZE_B		= register("compressed_bronze_block", TFBlocks.blockStorageAlloy.blockBronze);
					C_CONSTANTAN_B	= register("compressed_constantan_block", TFBlocks.blockStorageAlloy.blockConstantan);
					C_ELECTRUM_B	= register("compressed_electrum_block", TFBlocks.blockStorageAlloy.blockElectrum);
					C_ENDERIUM_B	= register("compressed_enderium_block", TFBlocks.blockStorageAlloy.blockEnderium);
					C_INVAR_B		= register("compressed_invar_block", TFBlocks.blockStorageAlloy.blockInvar);
					C_LUMIUM_B		= register("compressed_lumium_block", TFBlocks.blockStorageAlloy.blockLumium);
					C_SIGNALUM_B	= register("compressed_signalum_block", TFBlocks.blockStorageAlloy.blockSignalum);
					C_STEEL_B		= register("compressed_steel_block", TFBlocks.blockStorageAlloy.blockSteel);
					
				}catch(NoClassDefFoundError e) {}
			}
		}
		
		public static class Has {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "thermalfoundation");
		}
	}
	
	public static class EnderIOBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		
		public static class Normal {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		public static class Has {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		@SuppressWarnings("unused")
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "enderio");
		}
	}
	
	public static class ProjectEBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		
		public static class Normal {
			public static BaseBlockSub C_DARK_MATTER_B 		= null;
			public static BaseBlockSub C_RED_MATTER_B 		= null;
			public static BaseBlockSub C_ALCHEMICAL_COAL_B 	= null;
			public static BaseBlockSub C_MOBIUS_FUEL_B 		= null;
			public static BaseBlockSub C_AETERNALIS_FULE_B 	= null;
			
			static {
				try {
					
					C_DARK_MATTER_B 		= register("compressed_dark_matter_block", new ItemStack(ObjHandler.matterBlock, 1, 0));
					C_RED_MATTER_B 			= register("compressed_red_matter_block", new ItemStack(ObjHandler.matterBlock, 1, 1));
					C_ALCHEMICAL_COAL_B 	= register("compressed_alchemical_coal_block", new ItemStack(ObjHandler.fuelBlock, 1, 0));
					C_MOBIUS_FUEL_B			= register("compressed_mobius_fuel_block", new ItemStack(ObjHandler.fuelBlock, 1, 1));
					C_AETERNALIS_FULE_B 	= register("compressed_aeternalis_fuel_block", new ItemStack(ObjHandler.fuelBlock, 1, 2));
					
					/*
					C_DARK_MATTER_B 		= register("compressed_dark_matter_block", new ItemStack(Blocks.REPEATING_COMMAND_BLOCK, 1, 0));
					C_RED_MATTER_B 			= register("compressed_red_matter_block", new ItemStack(Blocks.REPEATING_COMMAND_BLOCK, 1, 1));
					C_ALCHEMICAL_COAL_B 	= register("compressed_alchemical_coal_block", new ItemStack(Blocks.REPEATING_COMMAND_BLOCK, 1, 0));
					C_MOBIUS_FUEL_B			= register("compressed_mobius_fuel_block", new ItemStack(Blocks.REPEATING_COMMAND_BLOCK, 1, 1));
					C_AETERNALIS_FULE_B 	= register("compressed_aeternalis_fuel_block", new ItemStack(Blocks.REPEATING_COMMAND_BLOCK, 1, 2));
					*/
				}catch(Exception e) {}
			}
		}
		
		public static class Has {
			public static BaseBlockSub C_LOW_COVALENCE_B 		= null;
			public static BaseBlockSub C_MEDIUM_COVALENCE_B 	= null;
			public static BaseBlockSub C_HIGH_COVALENCE_B 		= null;
			
			static {
				try {
					C_LOW_COVALENCE_B 		= register("compressed_low_covalence_dust_block", new ItemStack(ObjHandler.covalence, 1, 0));
					C_MEDIUM_COVALENCE_B 	= register("compressed_medium_covalence_dust_block", new ItemStack(ObjHandler.covalence, 1, 1));
					C_HIGH_COVALENCE_B 		= register("compressed_high_covalence_dust_block", new ItemStack(ObjHandler.covalence, 1, 2));
					
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			if(!JiuUtils.item.equalsStack(unCompressedItem, new ItemStack(Items.AIR), false)) {
				return BaseBlockSub.register(nameIn, unCompressedItem, "projecte");
			}else {
				return null;
			}
		}
	}
	
	public static class DraconicEvolutionBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		
		public static class Normal {
			public static BaseBlockSub C_DRACONIUM_BLOCK_B = null;
			public static BaseBlockSub C_DRACONIC_BLOCK_B = null;
			public static BaseBlockSub C_INFUSED_OBSIDIAN_BLOCK_B = null;
			public static BaseBlockSub C_CREATIVE_RF_SOURCE_B = null;
			
			static {
				try {
					C_DRACONIUM_BLOCK_B 		= register("compressed_draconium_block", new ItemStack(DEFeatures.draconiumBlock, 1, 0));
					C_DRACONIC_BLOCK_B 			= register("compressed_draconic_block", new ItemStack(DEFeatures.draconicBlock));
					C_INFUSED_OBSIDIAN_BLOCK_B 	= register("compressed_infused_obsidian_block", new ItemStack(DEFeatures.infusedObsidian));
					C_CREATIVE_RF_SOURCE_B 		= Loader.isModLoaded("draconicevolution") ? new CompressedCreativeRFSource("compressed_creative_rf_source", new ItemStack(DEFeatures.creativeRFSource)) : null;
					
				}catch(Exception e) {}
			}
		}
		
		public static class Has {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "draconicevolution");
		}
	}
	
	public static class EnvironmentalTechBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		

		public static class Has {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		
		public static class Normal {
			public static BaseBlockSub C_AETHIUM_BLOCK_B = null;
			public static BaseBlockSub C_MICA_BLOCK_B = null;
			public static BaseBlockSub C_LITHERITE_BLOCK_B = null;
			public static BaseBlockSub C_ERODIUM_BLOCK_B = null;
			public static BaseBlockSub C_KYRONITE_BLOCK_B = null;
			public static BaseBlockSub C_PLADIUM_BLOCK_B = null;
			public static BaseBlockSub C_IONITE_BLOCK_B = null;
			
			static {
				try {
					C_AETHIUM_BLOCK_B 	= register("compressed_aethium_block", "environmentaltech:aethium");
					C_MICA_BLOCK_B 		= register("compressed_mica_block", "environmentaltech:mica");
					C_LITHERITE_BLOCK_B = register("compressed_litherite_block", "environmentaltech:litherite");
					C_ERODIUM_BLOCK_B 	= register("compressed_erodium_block", "environmentaltech:erodium");
					C_KYRONITE_BLOCK_B 	= register("compressed_kyronite_block", "environmentaltech:kyronite");
					C_PLADIUM_BLOCK_B 	= register("compressed_pladium_block", "environmentaltech:pladium");
					C_IONITE_BLOCK_B 	= register("compressed_ionite_block", "environmentaltech:ionite");
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, String unCompressedItem) {
			return register(nameIn, unCompressedItem, 0);
		}
		
		private static BaseBlockSub register(String nameIn, String unCompressedItem, int meta) {
			return register(nameIn, new ItemStack(Block.getBlockFromName(unCompressedItem), 1, meta));
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "environmentaltech");
		}
	}
	
	public static class AvaritiaBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		protected static final morph.avaritia.init.ModBlocks blocks = new morph.avaritia.init.ModBlocks();
		protected static final morph.avaritia.init.ModItems items = new morph.avaritia.init.ModItems();
		
		public static class Normal {
			public static BaseBlockSub C_NEUTRONIUM_BLOCK_B = null;
			public static BaseBlockSub C_INFINITY_BLOCK_B = null;
			public static BaseBlockSub C_CRYSTAL_MATRIX_BLOCK_B = null;
			
			static {
				try {
					C_NEUTRONIUM_BLOCK_B 		= register("compressed_neutronium_block", new ItemStack(blocks.resource)).setInfoStack(new ItemStack(items.resource, 1, 4));
					C_INFINITY_BLOCK_B 	 		= register("compressed_infinity_block", new ItemStack(blocks.resource, 1, 1)).setInfoStack(new ItemStack(items.resource, 1, 6));
					C_CRYSTAL_MATRIX_BLOCK_B 	= register("compressed_crystal_matrix_block", new ItemStack(blocks.resource, 1, 2)).setInfoStack(new ItemStack(items.resource, 1, 1));
					
				}catch(Exception e) {}
			}
		}
		
		public static class Has {
			public static BaseBlockSub C_INFINITY_CATALYST_B = null;
			
			static {
				try {
					C_INFINITY_CATALYST_B = register("compressed_infinity_catalyst", items.infinity_catalyst).setIsTransparentCube().setInfoStack(items.infinity_catalyst);
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "avaritia");
		}
	}
	
	public static class TconstructBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		
		public static class Normal {
			public static BaseBlockSub C_CLEAR_GLASS_B 	= null;
			public static BaseBlockSub C_SOIL_B 		= null;
			public static BaseBlockSub C_SEARED_B 		= null;
			
			public static BaseBlockSub C_Cobalt_B 		= null;
			public static BaseBlockSub C_Ardite_B 		= null;
			public static BaseBlockSub C_Manyullyn_B 	= null;
			public static BaseBlockSub C_Knightslime_B 	= null;
			public static BaseBlockSub C_Pigiron_B 		= null;
			public static BaseBlockSub C_Alubrass_B 	= null;
			public static BaseBlockSub C_pearl_B	 	= null;
			
			static {
				try {
					C_Cobalt_B 		= register("compressed_cobalt_block", new ItemStack(TinkerCommons.blockMetal, 1, 0));
					C_Ardite_B 		= register("compressed_ardite_block", new ItemStack(TinkerCommons.blockMetal, 1, 1));
					C_Manyullyn_B 	= register("compressed_manyullyn_block", new ItemStack(TinkerCommons.blockMetal, 1, 2));
					C_Knightslime_B = register("compressed_knightslime_block", new ItemStack(TinkerCommons.blockMetal, 1, 3));
					C_Pigiron_B 	= register("compressed_pigiron_block", new ItemStack(TinkerCommons.blockMetal, 1, 4));
					C_Alubrass_B 	= register("compressed_alubrass_block", new ItemStack(TinkerCommons.blockMetal, 1, 5));
					C_pearl_B 		= register("compressed_pearl_block", new ItemStack(TinkerCommons.blockMetal, 1, 6));
					
					C_CLEAR_GLASS_B = register("compressed_clear_glass_block", new ItemStack(TinkerCommons.blockClearGlass));
					C_SOIL_B 		= register("compressed_soil_block", new ItemStack(TinkerCommons.blockSoil));
					C_SEARED_B 		= register("compressed_seared_block", new ItemStack(TinkerSmeltery.searedBlock, 1, 3));
					
				}catch(Exception e) {}
			}
		}
		
		public static class Has {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "tconstruct");
		}
	}
	
	public static class BotaniaBlock {
		public final Normal normal = new Normal();
		public final Has has = new Has();
		protected static vazkii.botania.common.block.ModBlocks blocks = new vazkii.botania.common.block.ModBlocks();
		
		public static class Normal {
			public static BaseBlockSub C_MANA_STEEL_B	 		= null;
			public static BaseBlockSub C_TERRASTELL_STEEL_B	 	= null;
			public static BaseBlockSub C_ELEMENTIUM_STELL_B	 	= null;
			public static BaseBlockSub C_MANA_DIAMOND_B	 		= null;
			public static BaseBlockSub C_DRAGONSTONE_B	 		= null;
			
			public static BaseBlockSub C_LIVING_WOOD_B	 		= null;
			public static BaseBlockSub C_LIVING_ROCK_B	 		= null;
			public static BaseBlockSub C_DREAMWOOD_B	 		= null;
			public static BaseBlockSub C_ELFGLASS_B	 			= null;
			public static BaseBlockSub C_MANA_GLASS_B	 		= null;
			public static BaseBlockSub C_SHIMMERROCK_B	 		= null;
			
			public static BaseBlockSub C_QUARTZ_TYPE_DARK_B	 	= null;
			public static BaseBlockSub C_QUARTZ_TYPE_MANA_B	 	= null;
			public static BaseBlockSub C_QUARTZ_TYPE_BLAZE_B	= null;
			public static BaseBlockSub C_QUARTZ_TYPE_LAVENDER_B	= null;
			public static BaseBlockSub C_QUARTZ_TYPE_RED_B	 	= null;
			public static BaseBlockSub C_QUARTZ_TYPE_EELF_B	 	= null;
			public static BaseBlockSub C_QUARTZ_TYPE_SUNNY_B	= null;
			
			static {
				try {
					C_MANA_STEEL_B 				= register("compressed_mana_stell_block", new ItemStack(blocks.storage, 1, 0));
					C_TERRASTELL_STEEL_B 		= register("compressed_terrasteel_stell_block", new ItemStack(blocks.storage, 1, 1));
					C_ELEMENTIUM_STELL_B 		= register("compressed_elementium_block", new ItemStack(blocks.storage, 1, 2));
					C_MANA_DIAMOND_B 			= register("compressed_mana_diamond_block", new ItemStack(blocks.storage, 1, 3));
					C_DRAGONSTONE_B 			= register("compressed_dragonstone_block", new ItemStack(blocks.storage, 1, 4));
					
					C_LIVING_WOOD_B 			= register("compressed_livingwood_block", new ItemStack(blocks.livingwood));
					C_LIVING_ROCK_B 			= register("compressed_livingrock_block", new ItemStack(blocks.livingrock));
					C_DREAMWOOD_B 				= register("compressed_dreamwood_block", new ItemStack(blocks.dreamwood));
					C_ELFGLASS_B 				= register("compressed_elfglass_block", new ItemStack(blocks.elfGlass));
					C_MANA_GLASS_B 				= register("compressed_managlass_block", new ItemStack(blocks.manaGlass));
					C_SHIMMERROCK_B 			= register("compressed_shimmerrock_block", new ItemStack(blocks.shimmerrock));
					
					C_QUARTZ_TYPE_DARK_B 		= register("compressed_quartz_dark_block", new ItemStack(ModFluffBlocks.darkQuartz));
					C_QUARTZ_TYPE_MANA_B 		= register("compressed_quartz_mana_block", new ItemStack(ModFluffBlocks.manaQuartz));
					C_QUARTZ_TYPE_BLAZE_B 		= register("compressed_quartz_blaze_block", new ItemStack(ModFluffBlocks.blazeQuartz));
					C_QUARTZ_TYPE_LAVENDER_B 	= register("compressed_quartz_lavender_block", new ItemStack(ModFluffBlocks.lavenderQuartz));
					C_QUARTZ_TYPE_RED_B 		= register("compressed_quartz_red_block", new ItemStack(ModFluffBlocks.redQuartz));
					C_QUARTZ_TYPE_EELF_B 		= register("compressed_quartz_eelf_block", new ItemStack(ModFluffBlocks.elfQuartz));
					C_QUARTZ_TYPE_SUNNY_B 		= register("compressed_quartz_sunny_block", new ItemStack(ModFluffBlocks.sunnyQuartz));
					
				}catch(Exception e) {}
			}
		}
		
		public static class Has {
			
			
			static {
				try {
					
					
				}catch(Exception e) {}
			}
		}
		
		private static BaseBlockSub register(String nameIn, ItemStack unCompressedItem) {
			return BaseBlockSub.register(nameIn, unCompressedItem, "botania");
		}
	}
	
	public static void registerCustom() {
		File config = new File("./config/jiu/mcs/custom.json");
		if(config.exists()) {
			try {
				JsonObject file = new JsonParser().parse(new FileReader(config)).getAsJsonObject();
				
				for(Map.Entry<String, JsonElement> fileObject : file.entrySet()) {
					JsonArray mainArray = fileObject.getValue().getAsJsonArray();// 主清�?
					for(int i = 0; i < mainArray.size(); ++i) {
						JsonObject subObject = mainArray.get(i).getAsJsonObject();//子清�?
						
						String type_tmp = subObject.get("type").getAsString();
						String[] main_type = type_tmp.indexOf(":") != -1 ? JiuUtils.other.custemSplitString(type_tmp, ":") : new String[]{type_tmp};
						
						CustomType type = CustomType.getType(main_type);
						if(type == CustomType.UNKNOWN) {
							String crashMsg = "\n\ncustom.json -> nunknown type: \n -> " + fileObject.getKey() + ": \n  -> (" + i + "):\n   -> \"type\": \"" + type_tmp + "\"\n";
//							MCS.proxy.makeCrashReport(crashMsg, new UnknownTypeException("nunknown type"));
							throw new UnknownTypeException(crashMsg);
						}else if(type == CustomType.BLOCK) {
							JsonArray entries = subObject.get("entries").getAsJsonArray();// 方块清单
							for(int m = 0; m < entries.size(); ++m) {
								JsonObject blockObject = entries.get(m).getAsJsonObject();
								
								String name = blockObject.get("id").getAsString();
								ItemStack unItem = InitChangeBlock.getStack(blockObject.get("unItem").getAsString());
								if(unItem == null || unItem.getItem() == Items.AIR || unItem == ItemStack.EMPTY) {
									String crashMsg = "\n\ncustom.json -> unknown item:\n -> " + fileObject.getKey() + ":\n  -> (" + i + "): \n   -> \"unItem\": \"" + blockObject.get("unItem").getAsString() + "\"\n";
//									MCS.proxy.makeCrashReport(crashMsg, new NonItemException("unknown item"));
									throw new NonItemException(crashMsg);
								}
//								CreativeTabs tab = getTab(blockObject);
								CreativeTabs tab = MCS.COMPERESSED_BLOCKS;
								BaseBlockSub block =  BaseBlockSub.register(name, unItem, "custom", tab);
								
								if(blockObject.has("enableDefaultRecipe")) {
									block.setMakeDefaultStackRecipe(blockObject.get("enableDefaultRecipe").getAsBoolean());
								}
								
								Map<Integer, ItemStack> InfoStackMap = InitCustomBlock.initInfoStack(blockObject);
								if(InfoStackMap != null) {
									block.setInfoStack(InfoStackMap);
								}
								
								Map<Integer, List<String>> infos = InitCustomBlock.initInfos(blockObject);
								if(infos != null) {
									block.addCustemInformation(infos);
								}
								
								Map<Integer, List<String>> shiftInfos = InitCustomBlock.initShiftInfos(blockObject);
								if(shiftInfos != null) {
									block.addCustemShiftInformation(shiftInfos);
								}
								
								Map<Integer, HarvestType> HarvestMap = InitCustomBlock.initHarvest(blockObject);
								if(HarvestMap != null) {
									block.setHarvestMap(HarvestMap);
								}
								
								Map<Integer, Float> HardnessMap = InitCustomBlock.initHardness(blockObject);
								if(HardnessMap != null) {
									block.setHardnessMap(HardnessMap);
								}
								
								Map<Integer, Boolean> BeaconBaseMap = InitCustomBlock.initBeaconBase(blockObject);
								if(BeaconBaseMap != null) {
									block.setBeaconBaseMap(BeaconBaseMap);
								}
								
								Map<Integer, Integer> LightValueMap = InitCustomBlock.initLightValue(blockObject);
								if(LightValueMap != null) {
									block.setLightValueMap(LightValueMap);
								}
								
								Map<Integer, Float> ExplosionResistanceMap = InitCustomBlock.initExplosionResistance(blockObject);
								if(ExplosionResistanceMap != null) {
									block.setExplosionResistanceMap(ExplosionResistanceMap);
								}
								
								Map<Integer, Boolean> UseWrenchBreakMap = InitCustomBlock.initUseWrenchBreak(blockObject);
								if(UseWrenchBreakMap != null) {
									block.canUseWrenchBreak(UseWrenchBreakMap);
								}
								
								MCSBlocks.SUB_BLOCKS_NAME.add(name);
								MCSBlocks.SUB_BLOCKS.add(block);
								MCSBlocks.SUB_BLOCKS_MAP.put(name, block);
							}
						}
					}
				}
			}catch(JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				e.printStackTrace();
				throw new JsonException("custom.json -> " + e.getLocalizedMessage().substring(e.getLocalizedMessage().indexOf(":")+2));
			}
		}
	}
	
	public static CreativeTabs getTab(JsonObject blockObject) {
		CreativeTabs tab = null;
		if(blockObject.has("tab")) {
			try {
				tab = getCreativeTabs(blockObject.get("tab").getAsString());
			} catch (Exception e) {
				e.printStackTrace();
				tab = MCS.COMPERESSED_BLOCKS;
			}
		}else {
			tab = MCS.COMPERESSED_BLOCKS;
		}
		return tab;
	}
	
	public static CreativeTabs getCreativeTabs(String name) {
		for(CreativeTabs tab : CreativeTabs.CREATIVE_TAB_ARRAY) {
//			Field f = tab.getClass().getDeclaredField("tabLabel");
//			f.setAccessible(true);
//			String tabName = (String) f.get(tab);
			
			if(tab.getTabLabel().toLowerCase().equals(name.toLowerCase())) {
				return tab;
			}
		}
		return null;
	}
	
	private static final Map<String, Map<Integer,ChangeBlockType>> CHANGE_BLOCK_MAP = Maps.newHashMap();
	
	public static final Map<String, Map<Integer,ChangeBlockType>> CHANGE_MCS_BLOCK_MAP = Maps.newHashMap();
	public static final Map<String, Map<Integer,ChangeBlockType>> CHANGE_OTHER_BLOCK_MAP = Maps.newHashMap();
	
	public static void reinitChangeBlock() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		for(int i = 0; i < CHANGE_BLOCK_MAP.size()+2; i++) {
			CHANGE_BLOCK_MAP.clear();
			CHANGE_MCS_BLOCK_MAP.clear();
			CHANGE_OTHER_BLOCK_MAP.clear();
		}
		
		initChangeBlock();
	}
	
	public static void initChangeBlock() throws JsonIOException, JsonSyntaxException, FileNotFoundException, NumberFormatException {		
		File config = new File("./config/jiu/mcs/changed.json");
		if(config.exists()) {
			JsonObject file = new JsonParser().parse(new FileReader(config)).getAsJsonObject();
			for(Map.Entry<String, JsonElement> jobj : file.entrySet()) {
				
				JsonArray arr = (JsonArray) jobj.getValue();// 主清�?
				for(int i = 0; i < arr.size(); ++i) {
					JsonObject obj = (JsonObject) arr.get(i);//子清�?
					
					String name = obj.get("block").getAsString();
					int[] metas = parseMeta("meta", obj);
					Map<Integer,ChangeBlockType> typeMap = Maps.newHashMap();
					
					InitChangeBlock.init(typeMap, obj, name, metas);
					CHANGE_BLOCK_MAP.put(name, typeMap);
				}
			}
		}
		
		for(String name : CHANGE_BLOCK_MAP.keySet()) {
			if(SUB_BLOCKS_NAME.contains(name)) {
				CHANGE_MCS_BLOCK_MAP.put(name, CHANGE_BLOCK_MAP.get(name));
			}else {
				CHANGE_OTHER_BLOCK_MAP.put(name, CHANGE_BLOCK_MAP.get(name));
			}
		}
	}
	
	private static int[] parseMeta(String times, JsonObject obj) {
		int[] metas = {0};
		if(obj.has("meta")) {
			JsonArray metas0 = obj.get("meta").getAsJsonArray();
			metas = new int[metas0.size()];
			for(int meta = 0; meta < metas0.size(); ++meta) {
				metas[meta] = metas0.get(meta).getAsInt();
			}
		}
		return metas;
	}
}
