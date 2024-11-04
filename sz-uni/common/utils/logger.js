import {parseTime} from "@/common/utils/base";

export const logger = Object.freeze({
  timeFormatter: '{y}-{m}-{d} {h}:{i}:{s}.{l}',
  LEVEL: {
    INFO: 'INFO',
    WARN: 'WARN',
    ERROR: 'ERROR',
  },
  log(level, ...msg) {
    const logStr = `${parseTime(new Date(), this.timeFormatter).padEnd(25)} ${ level.padEnd(10) } ${msg.join(', ')}`;
    console.log(logStr)
  },
  warn(...msg) {
    this.log(this.LEVEL.WARN, ...msg)
  },
  error(...msg) {
    this.log(this.LEVEL.ERROR, ...msg)
  },
  info(...msg) {
    this.log(this.LEVEL.INFO, ...msg)
  }
})
