echo "Creating temp. directory"
tempPath=$1/../temp
mkdir $tempPath


for entry in "$1"/*
do
    echo "Temp version of: $(basename $entry)"
    tail -n +2 $entry > $tempPath/$(basename $entry)
done

zipPath=$(basename $1)
rm -r $zipPath

echo "Creating zip: $zipPath"
zip -r -j $zipPath $tempPath 

rm -r $tempPath

