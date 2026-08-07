import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class Program {
	
	 // This example requires environment variables named "SPEECH_KEY" and "ENDPOINT"
    private static String speechKey = System.getenv("SPEECH_KEY");
    private static String endpoint = "https://eastasia.stt.speech.microsoft.com";
    		//System.getenv("ENDPOINT");
    private static Semaphore stopTranslationWithFileSemaphore;
    
    
    public static void main(String[] args) throws InterruptedException, ExecutionException, URISyntaxException {
        SpeechConfig speechConfig = SpeechConfig.fromEndpoint(new URI(endpoint), speechKey);
        fromFile(speechConfig);
    }

    public static void fromFile(SpeechConfig speechConfig) throws InterruptedException, ExecutionException {
        AudioConfig audioConfig = AudioConfig.fromWavFileInput("C:\\Users\\user\\Downloads\\LearningEnglishStories-20260501-ClassicStoriesLittleWomen.wav");
        SpeechRecognizer speechRecognizer = new SpeechRecognizer(speechConfig, audioConfig);
        
        Future<SpeechRecognitionResult> task = speechRecognizer.recognizeOnceAsync();
        SpeechRecognitionResult speechRecognitionResult = task.get();
        System.out.println("RECOGNIZED: Text=" + speechRecognitionResult.getText());
        
        switch (speechRecognitionResult.getReason()) {
        case RecognizedSpeech:
            System.out.println("We recognized: " + speechRecognitionResult.getText());
            int exitCode = 0;
            break;
        case NoMatch:
            System.out.println("NOMATCH: Speech could not be recognized.");
            break;
        case Canceled: {
                CancellationDetails cancellation = CancellationDetails.fromResult(speechRecognitionResult);
                System.out.println("CANCELED: Reason=" + cancellation.getReason());

                if (cancellation.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                    System.out.println("CANCELED: Did you set the speech resource key and region values?");
                }
            }
            break;
    }
    }
}
