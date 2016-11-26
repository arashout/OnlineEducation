def song_playlist(songs, max_size):
    """
    songs: list of tuples, ('song_name', song_len, song_size)
    max_size: float, maximum size of total songs that you can fit

    Start with the song first in the 'songs' list, then pick the next 
    song to be the one with the lowest file size not already picked, repeat

    Returns: a list of a subset of songs fitting in 'max_size' in the order 
             in which they were chosen.
    """
    playlist = []
    copy_songs = list(songs)
    #Pop first song - which is stupid
    if copy_songs[0][2] > max_size:
        return playlist
    else:
        cur_song = copy_songs.pop(0)
        playlist.append(cur_song[0])
        max_size -= cur_song[2]

    songs_sorted_by_size = sorted(copy_songs, key=lambda tup: tup[2])
    
    for i in range(len(songs_sorted_by_size)):
        cur_size = songs_sorted_by_size[i][2]
        cur_name = songs_sorted_by_size[i][0]
        if cur_size <= max_size:
            playlist.append(cur_name)
            max_size -= cur_size
        else:
            return playlist

    return playlist

print(song_playlist([('a', 4.4, 4.0), ('b', 3.5, 7.7), ('c', 5.1, 6.9), ('d', 2.7, 1.2)], 20))