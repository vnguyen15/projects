#!/bin/bash
echo "uploading...."

x=$(date +%s)

start_time=$(($1 + $x))

#echo $x >> append.txt
#echo $$start_time  >> append.txt

while [ $x -le $start_time ]
do
  
   x=$(date +%s) 

done

start=$(date +%s)

aws s3 cp 100MB.test s3://tcss562-test-files/100MB.test

end=$(date +%s)

result=$(( end - start ))
echo $result >> vm1_100MB_upload.txt
#echo "upload time: $result"   > 10GB.txt

aws s3 cp vm1_100MB_upload.txt s3://tcss562-test-files

