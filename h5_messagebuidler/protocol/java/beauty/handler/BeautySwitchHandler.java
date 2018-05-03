package app.game.handler.beauty;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;

import org.jboss.netty.buffer.ChannelBuffer;

import app.game.handler.BaseHeroFightHandler;
import app.game.module.equip.BeautyMessages;
import app.game.module.scene.HeroFightModule;

/**
 * 切换出战的美人
 * Created by CodeMaker
 * Created time: 2018-04-14 12:55:15
 */
@Handler(code=BeautyMessages.C2S_BEAUTY_SWITCH)
public class BeautySwitchHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int new_id  = BufferUtil.readVarInt32(buffer);  //新的出战美人ID
          final int old_id  = BufferUtil.readVarInt32(buffer);  //旧的收回的美人ID
         // 请在下面添加你的代码
    }
}
