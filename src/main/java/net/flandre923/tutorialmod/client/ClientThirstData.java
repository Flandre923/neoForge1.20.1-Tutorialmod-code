package net.flandre923.tutorialmod.client;

public class ClientThirstData {
    // 玩家的饥渴值，这个饥渴值有服务器发包得到。
    private static int playerThirst;

    /***
     * 大家好，这里是923，由于突然没有了录制环境，导致只能使用AI配音了，所以大家见谅
     * 这次我们添加饥渴值的绘制的HUD，就是游戏中的小瓶子，
     * 先写这个类，位于client包下，
     * 这个类是为在服务器发包的客户端时候修改的数值，方便客户端读取，因为没法直接修改玩家的cap的数值，借助这个类做中转，
     *
     *  那么我们启动游戏看一下
     * @param thirst
     */
    // 设置饥渴值
    public static void set(int thirst) {
        ClientThirstData.playerThirst = thirst;
    }
    // 获得饥渴值的数值
    public static int getPlayerThirst() {
        return playerThirst;
    }
}