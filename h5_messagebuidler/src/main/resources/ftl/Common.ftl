 <#macro ifWriteProperty ifs >
 	<#list ifs as ifWrite>
 	 <#if ifWrite.operation == "equals">
 	   if(${ifWrite.name}.equals(${ifWrite.value})){
  			<@writeProperty writeProperties=ifWrite.writeProperties/>
  	   } 
 	 </#if>
 	   if(${ifWrite.name}${ifWrite.operation}${ifWrite.value}){
 			<@writeProperty writeProperties=ifWrite.writeProperties/>
 	   }
 		
 	</#list>
 </#macro>
 
 <#macro ifReadProperty ifs>
	<#list ifs as ifRead>
	 <#if ifRead.operation == "equals">
	   if(${ifRead.name}.equals(${ifRead.value})){
			<@readProperty readProperties=ifRead.readProperties/>
	   } 
	 </#if>
	   if(${ifRead.name}${ifRead.operation}${ifRead.value}){
			<@readProperty readProperties=ifRead.readProperties/>
	   }
		
	</#list>
</#macro>
 
 <#macro writeProperty writeProperties>
   <#list writeProperties as writePro>
		<#if writePro.paramType=="base">
		responseMsg.set${writePro.type}(${writePro.name});
		<@ifWriteProperty ifs=writePro.ifs/>
		</#if>
		<#if writePro.paramType == "baseList">
		responseMsg.setShort(${writePro.name}.size());
		for (${writePro.type} temp${writePro.name}Value : ${writePro.name}){
			responseMsg.set${writePro.type1}(temp${writePro.name}Value);
		}
		</#if>
		<#if writePro.paramType == "bean">
		${writePro.name}.writeData(responseMsg);
		</#if>
		<#if writePro.paramType == "beanList">
		responseMsg.setShort(${writePro.name}.size());
		for (${writePro.type}Bean temp${writePro.type}Bean : ${writePro.name}){
			temp${writePro.type}Bean.writeData(responseMsg);
		}
		</#if>
    </#list>
</#macro>

<#macro readProperty readProperties>
	<#list readProperties as readPro>
		<#if readPro.paramType=="base">
		${readPro.name}=requestMsg.get${readPro.type}();
		<@ifReadProperty ifs=readPro.ifs/>
		</#if>
		<#if readPro.paramType == "baseList">
		${readPro.name}=new ArrayList<${readPro.type}>();
		int ${readPro.name}Size=requestMsg.getShort();
		for(int i=0;i<${readPro.name}Size;i++){
			${readPro.type} tempValue=requestMsg.get${readPro.type1}();
			${readPro.name}.add(tempValue);
		}
		</#if>
		<#if readPro.paramType == "bean">
		${readPro.name}=new ${readPro.type}Bean();
		${readPro.name}.readData(requestMsg);
		</#if>
		<#if readPro.paramType == "beanList">
		${readPro.name}=new ArrayList<${readPro.type}Bean>();
		int ${readPro.name}Size=requestMsg.getShort();
		for(int i=0;i<${readPro.name}Size;i++){
			${readPro.type}Bean tempBean=new ${readPro.type}Bean();
			tempBean.readData(requestMsg);
			${readPro.name}.add(tempBean);
		}
		</#if>
	</#list>
</#macro>
 