package app.game.handler.pet;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.pet.PetMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 使用宠物蛋,p1背包开始位置
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=PetMessages.C2S_PET_PETS_USE_BORN_EGG)
public class PetPetsUseBornEggHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int templateId  = BufferUtil.readVarInt32(buffer);  //宠物蛋模版ID
         // 请在下面添加你的代码
    }
}
