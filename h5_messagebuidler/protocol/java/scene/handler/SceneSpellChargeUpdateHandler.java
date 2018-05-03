package app.game.handler.scene;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.scene.SceneMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 蓄力技能更新
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=SceneMessages.C2S_SCENE_SPELL_CHARGE_UPDATE)
public class SceneSpellChargeUpdateHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final boolean isRelease  =  BufferUtil.readBoolean(buffer);  //是否释放
          final int targetX  = BufferUtil.readVarInt32(buffer);  //x坐标点
          final int targetY  = BufferUtil.readVarInt32(buffer);  //y坐标点
         // 请在下面添加你的代码
    }
}
