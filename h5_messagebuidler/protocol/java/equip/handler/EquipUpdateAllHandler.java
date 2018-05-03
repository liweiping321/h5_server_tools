package app.game.handler.equip;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.equip.EquipMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 一键换装-请求
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=EquipMessages.C2S_EQUIP_UPDATE_ALL)
public class EquipUpdateAllHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int heroPosId  = BufferUtil.readVarInt32(buffer);  //装备槽id
         // 请在下面添加你的代码
    }
}
