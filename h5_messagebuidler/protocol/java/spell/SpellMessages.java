package app.game.module.spell;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
/**
* 技能模块
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class SpellMessages {
    public static final int MODULE_ID = Modules.SPELL_MODULE_ID;


    /**
    *设置战斗技能
    */
    public static final int C2S_SPELL_SET_FIGHT_SPELL=4001;

    /**
    *设置战斗技能结果
    */
    public static final int S2C_SPELL_SET_FIGHT_SPELL_SUC=4002;
    public static ChannelBuffer createSetFightSpellSucBuffer(int slot,int useSpellId) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_SPELL_SET_FIGHT_SPELL_SUC);

        BufferUtil.writeVarInt32(buffer,slot); //设置的技能槽位
        BufferUtil.writeVarInt32(buffer,useSpellId); //设置的技能ID

        return buffer;
    }

}
