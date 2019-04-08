import ch.qos.logback.classic.filter.ThresholdFilter

import java.nio.charset.Charset

statusListener(OnConsoleStatusListener)

def PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%-2thread] [%X{ip},%X{traceId},%X{appName}] %highlight(%-5level) %logger{36}.%M\\(%line\\) - %highlight(%msg) %n"

def PATH = "/export/Logs/hugh"

appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
        charset = Charset.forName("utf-8")
    }
}

appender("FILE-ALL", RollingFileAppender) {
    file = "${PATH}/file-all.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
        charset = Charset.forName("utf-8")
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${PATH}/file-all.log.%d"
        maxHistory = 6
    }
}


appender("FILE-ERROR", RollingFileAppender) {
    file = "${PATH}/file-error.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = PATTERN
        charset = Charset.forName("utf-8")
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "${PATH}/file-error.log.%d"
        maxHistory = 6
    }
    filter(ThresholdFilter) {
        level = WARN
    }
}
//logger("com.jd.xstore.wms.basedata.biz.storehouse.WarehouseInitService", INFO, ["INIT_WAREHOUSE_LOG"], true)
logger("com.hugh.authentication.core.infrastructure.mysql", DEBUG)

//root(INFO, ["CONSOLE", "FILE-ALL", "FILE-ERROR"])
root(INFO, ["CONSOLE"])