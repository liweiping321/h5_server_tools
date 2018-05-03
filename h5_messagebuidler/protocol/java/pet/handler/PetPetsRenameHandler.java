package app.game.handler.pet;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.pet.PetMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 宠物改名,p1宠物ID
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=PetMessages.C2S_PET_PETS_RENAME)
public class PetPetsRenameHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final boolean isBind  =  BufferUtil.readBoolean(buffer);  //是否使用绑定点券
          final String nickname  =  BufferUtil.readUTF(buffer);  //宠物昵称
         // 请在下面添加你的代码
    }
}
