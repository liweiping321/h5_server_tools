        this.%JAVA_NAME% = new HashMap<>();
        for(%CONTENT_NAME%.%MODULE_PROTO_NAME%%SIDE%Proto it:obj.get%PROTO_NAME%List())
        {
            this.%JAVA_NAME%.put(it.getKey(),it.getValue());
        }
