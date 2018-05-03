package app.game.handler.scene;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.scene.SceneMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 获得最近的怪物坐标
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=SceneMessages.C2S_SCENE_GET_NEAREST_MONSTER)
public class SceneGetNearestMonsterHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
         // 请在下面添加你的代码
    }
}
