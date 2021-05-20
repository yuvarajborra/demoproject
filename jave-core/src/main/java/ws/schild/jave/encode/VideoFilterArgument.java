package ws.schild.jave.encode;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A SimpleArgument is an EncodingArgument that provides all of its components, The argument type
 * and a Function from EncodingAttributes to a Stream&lt;String&gt; (arguments to ffmpeg)
 *
 * @author mressler
 */
public class VideoFilterArgument implements EncodingArgument {

  private final ArgType argumentType;
  private final Function<EncodingAttributes, Stream<String>> getArguments;

  public VideoFilterArgument(
      ArgType argumentType, Function<EncodingAttributes, Stream<String>> getArguments) {
    this.argumentType = argumentType;
    this.getArguments = getArguments;
  }

  @Override
  public Stream<String> getArguments(EncodingAttributes context) {
    return Stream.concat(Stream.of("-vf"), getArguments.apply(context));
  }

  @Override
  public ArgType getArgType() {
    return argumentType;
  }
}
