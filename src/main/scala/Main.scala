import com.redislabs.provider.redis.streaming._
import com.redislabs.provider.redis.streaming.{ConsumerConfig, StreamItem}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Redis Stream Example")
      .master("local[*]")
      .config("spark.redis.host", "localhost")
      .config("spark.redis.port", "6380")
      .getOrCreate()

    val ssc = new StreamingContext(spark.sparkContext, Seconds(10))

    val stream = ssc.createRedisXStream(Seq(ConsumerConfig("mystream", "mygroup", "myconsumer1")))

    stream.foreachRDD {rdd =>
      rdd.foreach {si =>
        println("*"*50)
        println(si.fields)
        println("*"*50)
      }
    }

//    stream.print()

    ssc.start()
    ssc.awaitTermination()

  }
}