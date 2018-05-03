package com.xml;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class JaxbReadXml {

    @SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String context) throws JAXBException, IOException {
        try {
            InputStream inputStream = ClassLoader.getSystemResource(context).openStream();
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException,
            JAXBException {
        InputStream is = null;
        try {
            if (arguments.length > 0) {
                config = MessageFormat.format(config, arguments);
            }
            // logger.trace("read configFileName=" + config);
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            is = new FileInputStream(config);
            return (T) u.unmarshal(is);
        } catch (IOException e) {
            // logger.trace(config, e);
            throw e;
        } catch (JAXBException e) {
            // logger.trace(config, e);
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(dataStream);
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }


}
