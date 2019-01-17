package benchmark;

import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(new String[]{"benchmark.SerializationBenchmark"});
    }
}
