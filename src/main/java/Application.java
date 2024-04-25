///usr/bin/env java --source 22 --enable-preview --class-path ${APP_DIR:-.}/lib/'*' "$0" "$@"; exit $?

import language.api.Greeting;
import language.api.Greeting.Language;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.ExitCode;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "PolyglotHello", mixinStandardHelpOptions = true)
class Application implements Callable<Integer> {

    @Option(names = { "-l", "--language"}, required = true, description = "Valid values are: ${COMPLETION-CANDIDATES}")
    private Language language;

    @Override
    public Integer call() {
        System.out.println(Greeting.byLanguage(language).getGreeting());
        return ExitCode.OK;
    }

    void main(String[] args) {
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }
}
