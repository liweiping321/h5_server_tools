public class %MODULE_NAME%CfgPrvd extends BaseCfgProvider<%PRIMARY_FIELD_TYPE%,%MODULE_NAME%Cfg>{

    private %MODULE_NAME%CfgPrvd(){}

    @Override
    public void encode4Config(GeneratedMessage.Builder cfgBuilder){
        %MODULE_NAME%CfgProtos.Builder builder=%MODULE_NAME%CfgProtos.newBuilder();

        for(%MODULE_NAME%Cfg cfg:getConfigDatas()){
            builder.addCfg(cfg.encode4Config());
        }

        ((CfgProto.Builder)cfgBuilder).set%MODULE_NAME%Cfg(builder);
    }

    public Class<%MODULE_NAME%Cfg> getCfgClass(){
        return %MODULE_NAME%Cfg.class;
    }

    public static final %MODULE_NAME%CfgPrvd ins=new %MODULE_NAME%CfgPrvd();

//下方写逻辑------------------------------------------------------------------------------------------------------------

    @Override
    protected void afterLoad(%MODULE_NAME%Cfg cfg){
        System.out.println(JSON.toJSONString(cfg));
    }

    @Override
    protected void afterLoadAll(){}
}
