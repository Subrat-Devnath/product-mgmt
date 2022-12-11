import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.user.main.dto.UserDTO;

public class Test {

	public static void main(String[] args) throws Exception {

		List<UserDTO> list = new ArrayList<>();
		Map<String, UserDTO> ma = new HashMap<>();
		for (UserDTO s : list) {
			ma.put(s.getFirtName(), s);
		}
		System.out.println(ma);
	}

	void convertDate() throws ParseException {
		long currentTimeMillis = System.currentTimeMillis();

		Date date = new Date(currentTimeMillis);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formatted = format.format(date);
		System.out.println(formatted);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dat = dateFormat.parse(formatted);
		long epoch = dat.getTime();
		System.out.println(epoch);
	}

}
