package app.game.handler.example;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.example.ExampleMessages;
import app.game.module.scene.HeroFightModule;
import java.util.Date;
/**
 * 请求消息
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=ExampleMessages.C2S_EXAMPLE_GET)
public class ExampleGetHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final boolean name1  =  BufferUtil.readBoolean(buffer);  //测试描述
          final int name2  = BufferUtil.readByte(buffer);  //测试描述
          final int name4  =  BufferUtil.readShort(buffer);  //测试描述
          final int name6  = BufferUtil.readVarInt32(buffer);  //测试描述
          final String name7  =  BufferUtil.readUTF(buffer);  //测试描述
          final long name8  =  BufferUtil.readVarInt64(buffer);  //测试描述
          final Date name9  =  BufferUtil.readDate(buffer);  //测试描述
          final byte[] beauty_list  =  BufferUtil.readBytes(buffer);  //美人数据
          final int[] list_int  = BufferUtil.readInts(buffer);  //测试list_int
          final int[] list_short  =  BufferUtil.readShorts(buffer);  //测试list_short
          final long[] list_long  =  BufferUtil.readLongs(buffer);  //测试list_long
          final String[] list_string  =  BufferUtil.readUTFs(buffer);  //测试list_string
          final Date[] list_date  =  BufferUtil.readDates(buffer);  //测试list_date
         // 请在下面添加你的代码
    }
}
