package mcjty.ariente.biomes;

import mcjty.ariente.biomes.features.WorldGenBlueTree;
import mcjty.ariente.biomes.features.WorldGenGlassTree;
import mcjty.ariente.blocks.ModBlocks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;

import java.util.Random;

public abstract class AbstractArienteBiome extends Biome {

    protected static final WorldGenGlassTree GLASS_TREE = new WorldGenGlassTree(false);
    protected static final WorldGenHugeTrees HUGE_TREE = new WorldGenBlueTree(false,
            10, 20, ModBlocks.bluelog.getDefaultState(), ModBlocks.blueleaves.getDefaultState());

    public AbstractArienteBiome(BiomeProperties properties) {
        super(properties);
//        this.topBlock = Blocks.DIAMOND_BLOCK.getDefaultState();
//        this.fillerBlock = Blocks.MELON_BLOCK.getDefaultState();
    }

    @Override
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        generateBlocks(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return rand.nextInt(10) == 0 ? HUGE_TREE : GLASS_TREE;
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x448877;
//        return 0x3377aa;
    }

    @Override
    public int getWaterColorMultiplier() {
        return 0x55ff88;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x224477;
    }

    protected final void generateBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int i = worldIn.getSeaLevel();
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1) {
            if (j1 <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (iblockstate2.getBlock() == ModBlocks.marble) {
                    if (j == -1) {
                        if (k <= 0) {
                            iblockstate = AIR;
                            iblockstate1 = ModBlocks.marble.getDefaultState();
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
                                iblockstate = ICE;
                            } else {
                                iblockstate = WATER;
                            }
                        }

                        j = k;

                        if (j1 >= i - 1) {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        } else if (j1 < i - 7 - k) {
                            iblockstate = AIR;
                            iblockstate1 = ModBlocks.marble.getDefaultState();
                            chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
                        } else {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

                        if (j == 0 && iblockstate1.getBlock() == Blocks.SAND && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
                            iblockstate1 = iblockstate1.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? RED_SANDSTONE : SANDSTONE;
                        }
                    }
                }
            }
        }
    }
}
