package app.game.handler.pet;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.pet.PetMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 使用宠物药水,p1宠物ID
 * Created by CodeMaker
 * Created time: 2018-04-14 15:17:06
 */
@Handler(code=PetMessages.C2S_PET_PETS_USE_TRAIN_LIQUID)
public class PetPetsUseTrainLiquidHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int templateId  = BufferUtil.readVarInt32(buffer);  //药水模版ID
         // 请在下面添加你的代码
    }
}
