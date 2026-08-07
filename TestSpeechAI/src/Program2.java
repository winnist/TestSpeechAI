import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class Program2 {
	
	 // This example requires environment variables named "SPEECH_KEY" and "ENDPOINT"
    private static String speechKey = System.getenv("SPEECH_KEY");
    private static String endpoint = "https://eastasia.stt.speech.microsoft.com";
    		//System.getenv("ENDPOINT");
    private static Semaphore stopTranslationWithFileSemaphore;
    private static int length;
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, URISyntaxException {
        SpeechConfig speechConfig = SpeechConfig.fromEndpoint(new URI(endpoint), speechKey);
        AudioConfig audioConfig = AudioConfig.fromWavFileInput("C:\\Users\\user\\Downloads\\LearningEnglishStories-20260501-ClassicStoriesLittleWomen.wav");
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);
        
     // First initialize the semaphore.
        stopTranslationWithFileSemaphore = new Semaphore(0);

        /*int curLength = e.getResult().getText().length();
        	
            System.out.println(//"RECOGNIZING: Text=" + 
            	
            e.getResult().getText().substring(length, curLength));
            
            length = curLength;*/
       /* speechRecognizer.recognizing.addEventListener((s, e) -> {
        	int curLength = e.getResult().getText().length();
        	
            System.out.print(//"RECOGNIZING: Text=" +
            	
            e.getResult().getText().substring(length, curLength));
            
            length = curLength;
        });*/
        /*speechRecognizer.recognizing.addEventListener((s, e) -> {
            System.out.println("RECOGNIZING: Text=" + e.getResult().getText());
        });*/

        speechRecognizer.recognized.addEventListener((s, e) -> {
            if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
                System.out.println("RECOGNIZED: Text=" + e.getResult().getText());
            }
            else if (e.getResult().getReason() == ResultReason.NoMatch) {
                System.out.println("NOMATCH: Speech could not be recognized.");
            }
        });

        speechRecognizer.canceled.addEventListener((s, e) -> {
            System.out.println("CANCELED: Reason=" + e.getReason());

            if (e.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + e.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + e.getErrorDetails());
                System.out.println("CANCELED: Did you set the speech resource key and region values?");
            }

            stopTranslationWithFileSemaphore.release();
        });

        speechRecognizer.sessionStopped.addEventListener((s, e) -> {
            System.out.println("\n    Session stopped event.");
            stopTranslationWithFileSemaphore.release();
        });
        
     // Starts continuous recognition. Uses StopContinuousRecognitionAsync() to stop recognition.
        speechRecognizer.startContinuousRecognitionAsync().get();

        // Waits for completion.
        stopTranslationWithFileSemaphore.acquire();

        // Stops recognition.
        speechRecognizer.stopContinuousRecognitionAsync().get();
    }

    public static void fromFile(SpeechConfig speechConfig) throws InterruptedException, ExecutionException {
       
    }
}
