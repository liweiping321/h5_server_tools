package app.game.module.equip;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
/**
* 装备模块
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class EquipMessages {
    public static final int MODULE_ID = Modules.EQUIP_MODULE_ID;


    /**
    *穿装备-请求
    */
    public static final int C2S_EQUIP_PUT=6001;

    /**
    *脱装备-请求
    */
    public static final int C2S_EQUIP_OFF=6003;

    /**
    *一键换装-请求
    */
    public static final int C2S_EQUIP_UPDATE_ALL=6005;

}
