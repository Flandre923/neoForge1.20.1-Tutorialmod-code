package net.flandre923.tutorialmod.datagen;

import net.flandre923.tutorialmod.TutorialMod;
import net.flandre923.tutorialmod.block.ModBlocks;
import net.flandre923.tutorialmod.block.custom.BlueberryCropBlock;
import net.flandre923.tutorialmod.block.custom.ZirconLampBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
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
