package app.game.handler.pet;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.pet.PetMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 协议描述(协议头字段有什么用)
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=PetMessages.C2S_PET_REMOVE_OVERLORD)
public class PetRemoveOverlordHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int skillID  = BufferUtil.readVarInt32(buffer);  //测试描述
         // 请在下面添加你的代码
    }
}
