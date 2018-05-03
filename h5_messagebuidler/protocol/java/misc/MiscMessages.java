package app.game.module.misc;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
import java.util.Collection;
/**
* 消息模块
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class MiscMessages {
    public static final int MODULE_ID = Modules.MISC_MODULE_ID;


    /**
    *通用提示消息
    */
    public static final int S2C_MISC_TIPS=2002;
    public static ChannelBuffer createTipsBuffer(int tipsId,int type,int reqMsgCode,Collection<String> args) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_MISC_TIPS);

        BufferUtil.writeVarInt32(buffer,tipsId); //消息提示ID
        BufferUtil.writeVarInt32(buffer,type); //消息类型
        BufferUtil.writeVarInt32(buffer,reqMsgCode); //请求消息号
        BufferUtil.writeUTFs(buffer,args); //可变参数列表

        return buffer;
    }

    /**
    *GM命令
    */
    public static final int C2S_MISC_G_M_COMMOND=2011;

}
