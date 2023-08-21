package net.flandre923.tutorialmod.networking.packet;

import net.flandre923.tutorialmod.client.ClientThirstData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

// 添加这个数据包
public class ThirstDataSyncS2CPacket {
    // 传递的信息饥渴值
    private final int thirst;
    // 构建这个包时候传入饥渴值
    public ThirstDataSyncS2CPacket(int thirst) {
        this.thirst = thirst;
    }
    // 从buf中恢复这个包
    public ThirstDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.thirst = buf.readInt();
    }
    // 转为buf
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(thirst);
    }
    // 客户端处理
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            // 给我们之前写的ClientThirstData的类的饥渴值进行赋值。
            ClientThirstData.set(thirst);
        });
        return true;
    }
}