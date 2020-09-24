import com.google.common.io.CharStreams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


public class RPService {

    private static String username = System.getProperty("user.name");
    private static boolean loop_status = true;
    private final String API_ADDRESS = "reports.free.beeceptor.com/my/api/path";

    private List<String> lastState = new ArrayList<>();

    private Thread myThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String hostName = InetAddress.getLocalHost().getHostName();
                for(int i=1; ; i++) {
                    System.out.println("Scanning for processes for " + username + "@" + hostName + "...");
                    List<String> runningProcesses = getRunningProcesses();
                    System.out.println("Process count: " + runningProcesses.size());
                    if(!runningProcesses.equals(lastState)) {
                        System.out.println("New processes detected.");
                        System.out.println(runningProcesses.get(4));
                        // Business logic that sends process list to API
                        Report info = new Report(username, runningProcesses);
//                        sendRunningAppInfo(info);
                        System.out.println("New processes sent to API.");
                        lastState = runningProcesses;
                    }
                    Thread.sleep(30000);
                    if(!loop_status) {
                        break;
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    });

    public void run() {
        myThread.run();
    }

    private List<String> getRunningProcesses() throws Exception {
        // WINDOWS
        Process p = Runtime.getRuntime().exec("tasklist /FI \"username eq " + username + "\" /FI \"status eq running\"");
        // UNIX
//        Process p = Runtime.getRuntime().exec("ps aux -e");
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return CharStreams.readLines(input);
    }

    private void sendRunningAppInfo(Report info) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Report> request = new HttpEntity<>(info);
        System.out.println(request);
        restTemplate.postForObject(API_ADDRESS, request, Report.class);
    }
}
