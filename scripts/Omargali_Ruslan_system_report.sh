#!/bin/bash

STUDENT_NAME="Ruslan Omargali"
STUDENT_ID="37858"
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
LOG_FILE="$PROJECT_DIR/logs/Omargali_Ruslan_system_report.log"

mkdir -p "$PROJECT_DIR/logs"

{
    echo "=================================================="
    echo " SYSTEM REPORT"
    echo "=================================================="
    echo "Student:        $STUDENT_NAME (ID $STUDENT_ID)"
    echo "Date and time:  $(date '+%Y-%m-%d %H:%M:%S')"
    echo "User:           $(whoami)"
    echo "OS:             $(grep PRETTY_NAME /etc/os-release 2>/dev/null | cut -d= -f2 | tr -d '"')"
    echo "Kernel:         $(uname -r)"
    echo
    echo "---- CPU ----"
    lscpu 2>/dev/null | grep -E "Model name|^CPU\(s\)|Architecture" || nproc
    echo
    echo "---- Memory ----"
    free -h
    echo
    echo "---- Disk usage ----"
    df -h
    echo
    echo "---- Available space on / ----"
    df -h / | awk 'NR==2 {print "Available: " $4 " of " $2 " (used " $5 ")"}'
    echo
} | tee -a "$LOG_FILE"

echo "Report saved to $LOG_FILE"
