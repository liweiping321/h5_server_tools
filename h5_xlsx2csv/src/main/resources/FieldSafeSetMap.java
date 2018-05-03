        if(%JAVA_NAME%!=null){
            %CONTENT_NAME%.%MODULE_PROTO_NAME%%SIDE%Proto.Builder %JAVA_NAME%Builder=%CONTENT_NAME%.%MODULE_PROTO_NAME%%SIDE%Proto.newBuilder();

            for(Map.Entry%JAVA_MAP_TYPE%entry:%JAVA_NAME%.entrySet()){
                %JAVA_NAME%Builder.setKey(entry.getKey());
                %JAVA_NAME%Builder.setValue(entry.getValue());
                builder.add%PROTO_NAME%(%JAVA_NAME%Builder);
            }
        }