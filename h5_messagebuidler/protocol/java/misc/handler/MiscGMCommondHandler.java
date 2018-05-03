package app.game.handler.misc;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.misc.MiscMessages;
import app.game.module.scene.HeroFightModule;
/**
 * GM命令
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=MiscMessages.C2S_MISC_G_M_COMMOND)
public class MiscGMCommondHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final String cmd  =  BufferUtil.readUTF(buffer);  //命令内容
         // 请在下面添加你的代码
    }
}
