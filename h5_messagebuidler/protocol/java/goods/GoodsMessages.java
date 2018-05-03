package app.game.module.goods;

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
/**
* 背包模块
* Created by CodeMaker
* Created time: 2018-04-20 20:01:35
*/
public class GoodsMessages {
    public static final int MODULE_ID = Modules.GOODS_MODULE_ID;


    /**
    *回收物品-请求
    */
    public static final int C2S_GOODS_SELL=5001;

    /**
    *物品同步-响应
    */
    public static final int S2C_GOODS_SYN=5002;
    public static ChannelBuffer createSynBuffer(byte[] userGoodsList) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_GOODS_SYN);

        BufferUtil.writeBytes(buffer,userGoodsList); //物品变更列表

        return buffer;
    }

    /**
    *使用物品-请求
    */
    public static final int C2S_GOODS_USE=5003;

    /**
    *删除物品
    */
    public static final int S2C_GOODS_REMOVE=5004;
    public static ChannelBuffer createRemoveBuffer(long id) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_GOODS_REMOVE);

        BufferUtil.writeVarInt64(buffer,id); //物品唯一ID

        return buffer;
    }

    /**
    *物品数量变动
    */
    public static final int S2C_GOODS_COUNT_CHANGE=5006;
    public static ChannelBuffer createCountChangeBuffer(long id,int count) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, S2C_GOODS_COUNT_CHANGE);

        BufferUtil.writeVarInt64(buffer,id); //物品唯一ID
        BufferUtil.writeVarInt32(buffer,count); //物品个数

        return buffer;
    }

}
