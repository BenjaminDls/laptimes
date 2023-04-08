package fr.benjamindanlos.laptimes.AssettoCorsa.Decoder;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Field;

import static fr.benjamindanlos.laptimes.AssettoCorsa.Utils.*;

@RequiredArgsConstructor
public class PacketDecoder {
	private final Reader reader;

	public <T extends Serializable> T deserialize(byte[] data, Class<T> klass) throws Exception {
		Field[] declaredFields = klass.getDeclaredFields();
		try {
			klass.getConstructor().setAccessible(true);

			reader.fill(data);
			T newInstance = klass.newInstance();

			for (Field field : declaredFields) {
				field.setAccessible(true);
				if (field.getType().isPrimitive()) {
					if (field.getType().equals(Integer.TYPE)) {
						field.setInt(newInstance, reader.readInt());
					} else if (field.getType().equals(Float.TYPE)) {
						field.setFloat(newInstance, reader.readFloat());
					} else if (field.getType().equals(Boolean.TYPE)) {
						field.setBoolean(newInstance, reader.readBoolean());
					} else if (field.getType().equals(Character.TYPE)) {
						field.setChar(newInstance, reader.readChar());
					}
				} else {
					if (field.getType().equals(String.class)) {
						field.set(newInstance, reader.readString(CHARS_TO_READ));
					} else if (field.getType().equals(float[].class)) {
						int readSize = field.getName().equals(CAR_COORDINATES_FIELD_NAME) ? CAR_COORDINATES_ARR_LENGTH : DEFAULT_FLOAT_ARR_LENGTH;
						field.set(newInstance, reader.readFloats(readSize));
					}
				}
			}

			return newInstance;
		} catch (NoSuchMethodException e) {
			throw new Exception(String.format("Class %s does not have no params constructor", klass.getName()), e);
		} catch (IllegalAccessException e) {
			// this should never happen
			throw new RuntimeException("Field is not accessible.", e);
		} catch (InstantiationException e) {
			throw new Exception(String.format("Is not able to instantiate %s", klass.getName()), e);
		}
	}
}
