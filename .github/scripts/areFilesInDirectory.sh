files=$(ls ./src/$1/resources)
found=true
for i in $files ; do
    echo $i
    found=false
    break
done
echo echo "::set-output name=bool::$found"