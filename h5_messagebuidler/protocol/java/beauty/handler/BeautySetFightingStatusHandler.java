package app.game.handler.beauty;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.beauty.BeautyMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 设置美人出战状态
 * Created by CodeMaker
 * Created time: 2018-04-14 15:19:13
 */
@Handler(code=BeautyMessages.C2S_BEAUTY_SET_FIGHTING_STATUS)
public class BeautySetFightingStatusHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int slot  = BufferUtil.readByte(buffer);  //所在槽
          final int id  = BufferUtil.readVarInt32(buffer);  //美人ID
         // 请在下面添加你的代码
    }
}
