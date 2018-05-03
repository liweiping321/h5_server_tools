package app.game.handler.scene;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.scene.SceneMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 获取怪物的位置 
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=SceneMessages.C2S_SCENE_GET_MONSTER_POS)
public class SceneGetMonsterPosHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int monsterIdType  = BufferUtil.readVarInt32(buffer);  //怪物类型
          final int monsterId  = BufferUtil.readVarInt32(buffer);  //怪物Id
         // 请在下面添加你的代码
    }
}
