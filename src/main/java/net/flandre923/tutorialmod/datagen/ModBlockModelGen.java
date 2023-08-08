package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.block.custom.BlueberryCropBlock;
import net.flandre923.tutorialmod.block.custom.ZirconLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBlockModelGen extends BlockStateProvider {

    public ModBlockModelGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.registerBlockModelAndItem(ModBlocks.DEEPSLATE_ZIRCON_ORE.get());
        this.registerBlockModelAndItem(ModBlocks.ZIRCON_ORE.get());
        this.registerBlockModelAndItem(ModBlocks.ZIRCON_BLOCK.get());
        this.registerBlockModelAndItem(ModBlocks.JUMPY_BLOCK.get());
        this.registerBlockModelAndItem(ModBlocks.ENDSTONE_ZIRCON_ORE.get());
        this.registerBlockModelAndItem(ModBlocks.NETHERRACK_ZIRCON_ORE.get());
        this.registerBlockModelAndItem(ModBlocks.MY_GENERATOR_BLOCK.get());
        registerProcessor(ModBlocks.ZIRCON_LAMP.get());
        registerCrop(ModBlocks.BLUEBERRY_CROP.get());
        generateJasmineBlockState(ModBlocks.JASMINE.get());
        generatePottedJasmineBlockState(ModBlocks.POTTED_JASMINE.get());
        logBlock(((RotatedPillarBlock) ModBlocks.EBONY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.EBONY_WOOD.get(), blockTexture(ModBlocks.EBONY_LOG.get()), blockTexture(ModBlocks.EBONY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_EBONY_LOG.get(), new ResourceLocation(TutorialMod.MOD_ID, "block/stripped_ebony_log"),
                new ResourceLocation(TutorialMod.MOD_ID, "block/stripped_ebony_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_EBONY_WOOD.get(), new ResourceLocation(TutorialMod.MOD_ID, "block/stripped_ebony_log"),
                new ResourceLocation(TutorialMod.MOD_ID, "block/stripped_ebony_log"));

        registerBlockModelAndItem(ModBlocks.EBONY_PLANKS.get());
        registerBlockModelAndItem(ModBlocks.EBONY_LEAVES.get());
        saplingBlock(ModBlocks.EBONY_SAPLING.get());

        simpleBlockItem(ModBlocks.EBONY_LOG.get(), models().withExistingParent("tutorialmod:ebony_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.EBONY_WOOD.get(), models().withExistingParent("tutorialmod:ebony_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_EBONY_LOG.get(), models().withExistingParent("tutorialmod:stripped_ebony_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_EBONY_WOOD.get(), models().withExistingParent("tutorialmod:stripped_ebony_wood", "minecraft:block/cube_column"));
    }

    public void generateJasmineBlockState(Block block) {
        BlockModelBuilder model = models().cross(name(block), blockTexture(block)).renderType("cutout");
        simpleBlock(block,model);
        this.simpleBlockItem(block,model);
    }

    public void generatePottedJasmineBlockState(Block block) {
        simpleBlock(block,models().singleTexture(name(block),new ResourceLocation("block/flower_pot_cross"),"plant",new ResourceLocation(TutorialMod.MOD_ID,"block/jasmine")).renderType("cutout"));

    }


    public void  registerBlockModelAndItem(Block block){
         this.simpleBlockWithItem(block,this.cubeAll(block));
    }


    private void registerProcessor(Block block){
        ModelFile offModel = models().cubeAll(name(block)+"_off",blockTextureSuffix(block,"_off"));
        ModelFile onModel = models().cubeAll(name(block)+"_on",blockTextureSuffix(block,"_on"));

        getVariantBuilder(block).partialState().with(ZirconLampBlock.LIT,true).modelForState().modelFile(onModel).addModel()
                .partialState().with(ZirconLampBlock.LIT,false).modelForState().modelFile(offModel).addModel();

        simpleBlockItem(block,offModel);
    }

    private void registerCrop(Block block){
        int max_age = BlueberryCropBlock.MAX_AGE;
        VariantBlockStateBuilder variantBuilder = getVariantBuilder(block);
        for(int i=0;i<max_age;i++){
            variantBuilder.partialState().with(BlueberryCropBlock.AGE,i).modelForState()
                    .modelFile(models().crop(name(block).split("_")[0]+"_stage"+i,cropTextureSuffix(block,"_stage"+i)).renderType("cutout")).addModel();
        }
    }

    private void saplingBlock(Block block) {
        simpleBlock(block,
                models().cross(name(block),blockTexture(block)).renderType("cutout"));
    }

    public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath());
    }

    public ResourceLocation blockTextureSuffix(Block block,String suffix) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath() + suffix);
    }

    public ResourceLocation cropTextureSuffix(Block block,String suffix) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath().split("_")[0] + suffix);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }



}
