package app.game.module.${moduleName};

import app.game.module.Modules;
import com.mokylin.sink.util.BufferUtil;
import org.jboss.netty.buffer.ChannelBuffer;
import static com.mokylin.sink.util.BufferUtil.newDynamicMessage;
import static com.mokylin.sink.util.BufferUtil.onlySendHeaderMessage;
<#list imports as import >
import ${import};
</#list>
/**
* ${desc}
* Created by CodeMaker
* Created time: ${dateStr}
*/
public class ${className} {
    public static final int MODULE_ID = Modules.${uModuleName}_MODULE_ID;

    <#list protocols as protocol  >

    /**
    *${protocol.desc}
    */
    public static final int ${protocol.codeName}=${protocol.code};
    <#if protocol.sc2Info??>
    public static ChannelBuffer create${protocol.uName}Buffer(${protocol.sc2Info.paramAttrs}) {
        ChannelBuffer buffer = newDynamicMessage(MODULE_ID, ${protocol.codeName});

        <#list protocol.sc2Info.attrs as atrr  >
        ${atrr}
        </#list>

        return buffer;
    }
    </#if>
     <#if protocol.msgName??>
     public static final ChannelBuffer ${protocol.msgName}=onlySendHeaderMessage(MODULE_ID,${protocol.codeName});
     </#if>
    </#list>

}
