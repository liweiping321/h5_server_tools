package app.game.handler.scene;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.scene.SceneMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 释放技能
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=SceneMessages.C2S_SCENE_RELEASE_SPELL)
public class SceneReleaseSpellHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int spellType  = BufferUtil.readVarInt32(buffer);  //技能类型
          final int targetX  = BufferUtil.readVarInt32(buffer);  //x坐标点
          final int targetY  = BufferUtil.readVarInt32(buffer);  //y坐标点
          final long targetId  =  BufferUtil.readVarInt64(buffer);  //目标Id
         // 请在下面添加你的代码
    }
}
