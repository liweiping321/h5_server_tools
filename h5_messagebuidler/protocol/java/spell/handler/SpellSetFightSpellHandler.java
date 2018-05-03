package app.game.handler.spell;

import com.mokylin.handler.anno.Handler;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import app.game.handler.BaseHeroFightHandler;
import app.game.module.spell.SpellMessages;
import app.game.module.scene.HeroFightModule;
/**
 * 设置战斗技能
 * Created by CodeMaker
 * Created time: 2018-04-20 20:01:35
 */
@Handler(code=SpellMessages.C2S_SPELL_SET_FIGHT_SPELL)
public class SpellSetFightSpellHandler extends BaseHeroFightHandler {
    @Override
    public void doHandler(HeroFightModule hfm, ChannelBuffer buffer) throws Exception {
         // 以下代码为自动生成，请不要随意修改
          final int selectSpellId  = BufferUtil.readVarInt32(buffer);  //设置的技能ID
         // 请在下面添加你的代码
    }
}
