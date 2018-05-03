package app.game.module.beauty;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
/**
* 美人模块
* Created by CodeMaker
* Created time: 2018-04-14 15:19:13
*/
public class BeautyMessages {
    public static final int MODULE_ID = Modules.BEAUTY_MODULE_ID;


    /**
    *当前玩家所拥有的美人
    */
    public static final int S2C_BEAUTY_BEAUTY_LIST=100002;
    public static ChannelBuffer createBeautyListBuffer(找不到类型： type=proto, name=beauty_list) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_BEAUTY_BEAUTY_LIST);

        找不到类型： type=proto, name=beauty_list

        return buffer;
    }

    /**
    *激活美人
    */
    public static final int C2S_BEAUTY_ACTIVE=100003;

    /**
    *激活美人成功
    */
    public static final int S2C_BEAUTY_ACTIVE_SUCCESS=100004;
    public static ChannelBuffer createActiveSuccessBuffer(找不到类型： type=herobeautyproto, name=beauty) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_BEAUTY_ACTIVE_SUCCESS);

        找不到类型： type=herobeautyproto, name=beauty

        return buffer;
    }

    /**
    *设置美人出战状态
    */
    public static final int C2S_BEAUTY_SET_FIGHTING_STATUS=100005;

    /**
    *设置美人状态成功
    */
    public static final int S2C_BEAUTY_FIGHTING_STATUS=100006;
    public static ChannelBuffer createFightingStatusBuffer(byte slot,int id) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_BEAUTY_FIGHTING_STATUS);

        BufferUtil.writeByte(buffer,slot); //所在槽
        BufferUtil.writeVarInt32(buffer,id); //美人ID

        return buffer;
    }

    /**
    *美人经验值/等级变化
    */
    public static final int S2C_BEAUTY_BEAUTY_EXP=100010;
    public static ChannelBuffer createBeautyExpBuffer(int id,long exp,long max_exp,int level) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_BEAUTY_BEAUTY_EXP);

        BufferUtil.writeVarInt32(buffer,id); //美人ID
        BufferUtil.writeVarInt64(buffer,exp); //最新的经验值
        BufferUtil.writeVarInt64(buffer,max_exp); //最大经验值
        BufferUtil.writeVarInt32(buffer,level); //最新的等级

        return buffer;
    }

}
