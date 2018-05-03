package app.game.handler.scene;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.scene.SceneMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 请求复活
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=SceneMessages.C2S_SCENE_REQUEST_RELIVE)
public class SceneRequestReliveHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int reliveType  = BufferUtil.readVarInt32(buffer);  //复活类型
         // 请在下面添加你的代码
    }
}
