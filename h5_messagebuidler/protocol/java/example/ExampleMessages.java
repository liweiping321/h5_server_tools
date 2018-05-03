package app.game.module.example;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
import java.util.Date;
import java.util.Collection;
/**
* 示例
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class ExampleMessages {
    public static final int MODULE_ID = Modules.EXAMPLE_MODULE_ID;


    /**
    *请求消息
    */
    public static final int C2S_EXAMPLE_GET=1000001;

    /**
    *响应消息
    */
    public static final int S2C_EXAMPLE_SEND=1000002;
    public static ChannelBuffer createSendBuffer(boolean name1,byte name2,int name4,int name6,String name7,long name8,Date name9,byte[] beauty_list,Collection<Integer> list_int,Collection<Integer> list_short,Collection<Long> list_long,Collection<String> list_string,Collection<Date> list_date) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_EXAMPLE_SEND);

        BufferUtil.writeBoolean(buffer,name1); //测试描述
        BufferUtil.writeByte(buffer,name2); //测试描述
        BufferUtil.writeShort(buffer,name4); //测试描述
        BufferUtil.writeVarInt32(buffer,name6); //测试描述
        BufferUtil.writeUTF(buffer,name7); //测试描述
        BufferUtil.writeVarInt64(buffer,name8); //测试描述
        BufferUtil.writeDate(buffer,name9); //测试描述
        BufferUtil.writeBytes(buffer,beauty_list); //美人数据
        BufferUtil.writeInts(buffer,list_int); //测试list_int
        BufferUtil.writeShorts(buffer,list_short); //测试list_short
        BufferUtil.writeLongs(buffer,list_long); //测试list_long
        BufferUtil.writeUTFs(buffer,list_string); //测试list_string
        BufferUtil.writeDates(buffer,list_date); //测试list_date

        return buffer;
    }

    /**
    *测试空消息
    */
    public static final int S2C_EXAMPLE_IS_EMPTY_MESSAGE=1000004;
     public static final ChannelBuffer IS_EMPTY_MESSAGE=onlySendHeaderMessage(MODULE_ID,S2C_EXAMPLE_IS_EMPTY_MESSAGE);

}
