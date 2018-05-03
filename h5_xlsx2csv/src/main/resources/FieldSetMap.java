        for(Map.Entry%JAVA_MAP_TYPE% entry:%JAVA_NAME%.entrySet()) {
            %MODULE_PROTO_NAME%%SIDE%Proto.Builder %JAVA_NAME%ProtoBuilder=%MODULE_PROTO_NAME%%SIDE%Proto.newBuilder();
            %JAVA_NAME%ProtoBuilder.setKey(entry.getKey());
            %JAVA_NAME%ProtoBuilder.setValue(entry.getValue());
            builder.add%PROTO_NAME%(%JAVA_NAME%ProtoBuilder);
        }
