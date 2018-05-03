package app.game.handler.pet;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.pet.PetMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 开启被动技能栏位结果,p1宠物ID
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=PetMessages.C2S_PET_PETS_OPEN_SKILL_BOX1)
public class PetPetsOpenSkillBox1Handler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final boolean success  =  BufferUtil.readBoolean(buffer);  //成功失败
         // 请在下面添加你的代码
    }
}
