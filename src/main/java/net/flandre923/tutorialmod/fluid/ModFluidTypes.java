package net.flandre923.tutorialmod.fluid;

import net.flandre923.tutorialmod.TutorialMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

/**
 * 我们在这里添加流体的type
 */
public class ModFluidTypes {
    // 知名流体的贴图的位置 ， 这里的是原版的贴图的位置，在原版的block下面。
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    // 流体的效果的图的位置，这个位置我们的mod的贴图的位置
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(TutorialMod.MOD_ID, "misc/in_soap_water");

    // 获得deferredRegister的流体type的注册对象
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TutorialMod.MOD_ID);

    // 注册一个流体的type 分别是流体ide亮度，粘度等等。
    public static final RegistryObject<FluidType> SOAP_WATER_FLUID_TYPE = register("soap_water_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));


    // 注册方法传入name和配置
    // 其中new的是我们对fluidtype的包装类。
    // 其中的tintcolorl是我们雾的颜色。
    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}