package com.memesoundboard3d.app

import android.media.MediaPlayer
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.memesoundboard3d.app.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int = -1
    private lateinit var vibrator: Vibrator
    private lateinit var adapter: SoundAdapter
    private var allSounds: List<Sound> = emptyList()
    private var currentCategory = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vibrator = ContextCompat.getSystemService(this, Vibrator::class.java)!!
        
        setupSounds()
        setupRecyclerView()
        setupCategoryTabs()
        setupClickListeners()
    }

    private fun setupSounds() {
        allSounds = listOf(
            Sound("Vine Boom", "https://cdn.instants.meme/2026/01/18/vine-boom-sound.mp3", "💥", "classic"),
            Sound("Bruh", "https://cdn.instants.meme/2026/01/18/bruh.mp3", "🤦", "classic"),
            Sound("Sad Violin", "https://cdn.instants.meme/2026/01/18/sad-violin-the-meme-one.mp3", "🎻", "classic"),
            Sound("MLG Air Horn", "https://cdn.instants.meme/2026/01/18/mlg-air-horn.mp3", "📯", "classic"),
            Sound("Sanctuary Guardian", "https://cdn.instants.meme/2026/01/18/what-bottom-text-meme-sanctuary-guardian-s.mp3", "🏛️", "classic"),
            Sound("Dun Dun Dun", "https://cdn.instants.meme/2026/01/18/dun-dun-dunnnnnnnn.mp3", "🥁", "classic"),
            Sound("Metal Gear Alert", "https://cdn.instants.meme/2026/01/18/metal-gear-solid-alert.mp3", "⚠️", "classic"),
            Sound("Outro Song", "https://cdn.instants.meme/2026/01/18/outro-song.mp3", "🎵", "classic"),
            Sound("Buzzer", "https://cdn.instants.meme/2026/01/18/buzzer.mp3", "📢", "classic"),
            Sound("Censor Beep", "https://cdn.instants.meme/2026/01/18/censor-beep.mp3", "🔇", "classic"),
            Sound("Ding", "https://cdn.instants.meme/2026/01/18/ding-sound-effect.mp3", "🔔", "classic"),
            Sound("Punch", "https://cdn.instants.meme/2026/01/18/punch-sound.mp3", "👊", "classic"),
            Sound("Fart", "https://cdn.instants.meme/2026/01/18/fart.mp3", "💨", "classic"),
            Sound("Taco Bell Bong", "https://cdn.instants.meme/2026/01/18/taco-bell-bong.mp3", "🔔", "classic"),
            Sound("Discord Notif", "https://cdn.instants.meme/2026/01/18/discord-notification.mp3", "💬", "classic"),
            Sound("Discord Call", "https://cdn.instants.meme/2026/01/19/discord-call.mp3", "📞", "classic"),
            Sound("Metal Pipe", "https://cdn.instants.meme/2026/01/18/metal-pipe-clang.mp3", "🔩", "classic"),
            Sound("Instagram Thud", "https://cdn.instants.meme/2026/01/18/instagram-thud.mp3", "📱", "classic"),
            Sound("Meme Sound", "https://cdn.instants.meme/2026/01/18/meme.mp3", "🎭", "classic"),
            Sound("Apple Pay", "https://cdn.instants.meme/2026/01/18/apple-pay.mp3", "💳", "classic"),
            Sound("67", "https://cdn.instants.meme/2026/01/18/67.mp3", "🔢", "classic"),
            Sound("Lobotomy Sound", "https://cdn.instants.meme/2026/01/18/lobotomy-sound-effect.mp3", "🧠", "classic"),
            Sound("Anime Ahh", "https://cdn.instants.meme/2026/01/18/anime-ahh.mp3", "🌸", "classic"),
            Sound("Awkward Cricket", "https://cdn.instants.meme/2026/01/19/awkward-cricket.mp3", "🦗", "classic"),
            Sound("Discord Leave", "https://cdn.instants.meme/2026/01/18/discord-leave-noise.mp3", "🚪", "classic"),
            Sound("Mouse Click", "https://cdn.instants.meme/2026/01/18/mouse-click-sound.mp3", "🖱️", "classic"),
            Sound("Fnaf Music Box", "https://cdn.instants.meme/2026/01/19/fnaf-1-music-box.mp3", "🎭", "classic"),
            Sound("GTA Wasted", "https://cdn.instants.meme/2026/01/19/gta-v-wasted.mp3", "💀", "classic"),
            Sound("Pluh", "https://cdn.instants.meme/2026/01/18/pluh.mp3", "🗣️", "classic"),
            Sound("Iphone Notif", "https://cdn.instants.meme/2026/01/18/iphone-notification.mp3", "📱", "classic"),
            Sound("Emotional Damage", "https://cdn.instants.meme/2026/01/18/emotional-damage-meme.mp3", "😱", "viral"),
            Sound("Tuco Get Out", "https://cdn.instants.meme/2026/01/18/tuco-get-out.mp3", "🏃", "viral"),
            Sound("Galaxy Meme", "https://cdn.instants.meme/2026/01/18/galaxy-meme.mp3", "🌌", "viral"),
            Sound("Spider-Man Song", "https://cdn.instants.meme/2026/01/18/spiderman-meme-song.mp3", "🕷️", "viral"),
            Sound("Rizz Sound", "https://cdn.instants.meme/2026/01/18/rizz-sound-effect.mp3", "😎", "viral"),
            Sound("Run Vine", "https://cdn.instants.meme/2026/01/18/run-vine.mp3", "🏃", "viral"),
            Sound("Wide Putin", "https://cdn.instants.meme/2026/01/19/wide-putin-meme.mp3", "🇷🇺", "viral"),
            Sound("Weeknd Rizz", "https://cdn.instants.meme/2026/01/18/the-weeknd-rizzz.mp3", "🎤", "viral"),
            Sound("Social Credit", "https://cdn.instants.meme/2026/01/18/999-social-credit-siren.mp3", "🚨", "viral"),
            Sound("Oh My God Bro", "https://cdn.instants.meme/2026/01/18/oh-my-god-bro-oh-hell-nah-man.mp3", "😤", "viral"),
            Sound("Zvuk Litvina", "https://cdn.instants.meme/2026/01/18/zvuk-litvina.mp3", "🇱🇹", "viral"),
            Sound("Mr Beast Money", "https://cdn.instants.meme/2026/01/19/mr-beast-give-me-sum-money.mp3", "💰", "viral"),
            Sound("Skibidi Toilet", "https://cdn.instants.meme/2026/01/18/my-mommy-said-no-more-skibidi-toilet.mp3", "🚽", "viral"),
            Sound("Asian Huh", "https://cdn.instants.meme/2026/01/18/asian-meme-huh.mp3", "🤔", "viral"),
            Sound("Womp Womp", "https://cdn.instants.meme/2026/01/19/womp-womp-womp.mp3", "😢", "viral"),
            Sound("Vizg Svini", "https://cdn.instants.meme/2026/01/19/vizg-svini.mp3", "🐷", "viral"),
            Sound("Gudok Poezda", "https://cdn.instants.meme/2026/01/19/gudok-poezda.mp3", "🚂", "viral"),
            Sound("Yeah Boiii", "https://cdn.instants.meme/2026/01/18/yeah-boiii-i-i-i.mp3", "🎉", "viral"),
            Sound("Let Me Know", "https://cdn.instants.meme/2026/01/18/let-me-know.mp3", "📢", "viral"),
            Sound("Mac Quack", "https://cdn.instants.meme/2026/01/18/mac-quack.mp3", "🦆", "viral"),
            Sound("Huh Ceeday", "https://cdn.instants.meme/2026/01/18/huh-ceeday.mp3", "❓", "viral"),
            Sound("Haha Funny Laugh", "https://cdn.instants.meme/2026/01/18/haha-funny-laugh.mp3", "😂", "viral"),
            Sound("Heavenly Music", "https://cdn.instants.meme/2026/01/18/heavenly-musiic.mp3", "😇", "viral"),
            Sound("Core Sound", "https://cdn.instants.meme/2026/01/18/core-sound-effect.mp3", "🎯", "viral"),
            Sound("We Are Charlie Kirk", "https://cdn.instants.meme/2026/01/18/we-are-charlie-kirk.mp3", "📢", "viral"),
            Sound("Pop Sfx", "https://cdn.instants.meme/2026/01/19/pop-sfx.mp3", "🔊", "viral"),
            Sound("Na Na Na", "https://cdn.instants.meme/2026/01/18/na-na-na.mp3", "🎵", "viral"),
            Sound("Among Us Reveal", "https://cdn.instants.meme/2026/01/18/among-us-role-reveal-sound.mp3", "ඞ", "gaming"),
            Sound("Fortnite Death", "https://cdn.instants.meme/2026/01/18/death-sound-fortnite.mp3", "💀", "gaming"),
            Sound("Mario Jump", "https://cdn.instants.meme/2026/01/18/mario-jump.mp3", "🍄", "gaming"),
            Sound("Prowler", "https://cdn.instants.meme/2026/01/18/prowler-sound-effect.mp3", "🕷️", "gaming"),
            Sound("Brainrot Troll", "https://cdn.instants.meme/2026/01/19/strawberry-elephant-spawn-troll-steal-a-brainrot.mp3", "🧠", "gaming"),
            Sound("67 Kid", "https://cdn.instants.meme/2026/01/19/67-kid.mp3", "👶", "gaming"),
            Sound("Goku Drip", "https://cdn.instants.meme/2026/01/18/goku-drip.mp3", "🐉", "gaming"),
            Sound("Low Honor RDR2", "https://cdn.instants.meme/2026/01/18/low-honor-rdr-2.mp3", "🤠", "gaming"),
            Sound("Lagging Loading", "https://cdn.instants.meme/2026/01/18/lagging-loading.mp3", "⏳", "gaming"),
            Sound("Daddyy Chill", "https://cdn.instants.meme/2026/01/18/daddyy-chill.mp3", "🧊", "gaming"),
            Sound("Stranger Things Clock", "https://cdn.instants.meme/2026/01/18/stranger-things-clock-sound.mp3", "⏰", "gaming"),
            Sound("Correct Answer", "https://cdn.instants.meme/2026/01/18/correct-answer-gameshow.mp3", "✅", "gaming"),
            Sound("Smoke Detector Beep", "https://cdn.instants.meme/2026/01/18/smoke-detector-beep.mp3", "🚨", "gaming"),
            Sound("Man Snoring", "https://cdn.instants.meme/2026/01/19/man-snoring-meme.mp3", "😴", "gaming"),
            Sound("He He He Ha", "https://cdn.instants.meme/2026/01/18/he-he-he-ha-clash-royale-deep-fried.mp3", "👑", "gaming"),
            Sound("Lego Breaking", "https://cdn.instants.meme/2026/01/18/lego-breaking.mp3", "🧱", "gaming"),
            Sound("Anime Wow", "https://cdn.instants.meme/2026/01/18/anime-wow.mp3", "😮", "anime"),
            Sound("Huh Cat", "https://cdn.instants.meme/2026/01/19/huh-cat.mp3", "🙀", "anime"),
            Sound("MEOW", "https://cdn.instants.meme/2026/01/18/m-e-o-w.mp3", "🐱", "anime"),
            Sound("Wow Anime", "https://cdn.instants.meme/2026/01/19/wow-anime-meme.mp3", "😮", "anime"),
            Sound("Where Are You Going", "https://cdn.instants.meme/2026/01/19/where-are-you-goingg.mp3", "🏃", "anime"),
            Sound("Michael Dont Leave", "https://cdn.instants.meme/2026/01/18/michael-dont-leave-me-here.mp3", "😢", "anime"),
            Sound("We Are Charlie Kirk Loud", "https://cdn.instants.meme/2026/01/18/we-are-charlie-kirk-loud-asf.mp3", "📢", "anime"),
            Sound("Ngakak Laugh", "https://cdn.instants.meme/2026/01/18/ngakak-laugh-annoying.mp3", "😂", "anime"),
            Sound("YAAAAAAAAY", "https://cdn.instants.meme/2026/01/18/yaaaaaaaay.mp3", "🎉", "anime"),
            Sound("Mambo Anime", "https://cdn.instants.meme/2026/01/19/mamboman-bo-shi-ge-ju-matikanetannhauser.mp3", "🎵", "anime"),
            Sound("Fahhhhh", "https://cdn.instants.meme/2026/01/18/fahhhhh.mp3", "😫", "anime"),
            Sound("Please Speed", "https://cdn.instants.meme/2026/01/18/please-speed-i-need-this.mp3", "🏎️", "anime"),
            Sound("City Boy", "https://cdn.instants.meme/2026/01/18/city-boy.mp3", "🏙️", "anime"),
            Sound("Lizard Button", "https://cdn.instants.meme/2026/01/18/lizard-button.mp3", "🦎", "anime"),
            Sound("SpongeBob Fail", "https://cdn.instants.meme/2026/01/18/spongebob-fail.mp3", "🧽", "funny"),
            Sound("Snore Mimimi", "https://cdn.instants.meme/2026/01/18/snore-mimimimimimi.mp3", "😴", "funny"),
            Sound("Baby Laughing", "https://cdn.instants.meme/2026/01/18/baby-laughing-meme.mp3", "👶", "funny"),
            Sound("Cat Laugh", "https://cdn.instants.meme/2026/01/18/cat-laugh-meme-1.mp3", "🐱", "funny"),
            Sound("Sad Meow", "https://cdn.instants.meme/2026/01/18/sad-meow-song.mp3", "😿", "funny"),
            Sound("Door Knocking", "https://cdn.instants.meme/2026/01/18/door-knocking-sound-effect.mp3", "🚪", "funny"),
            Sound("SpongeBob Later", "https://cdn.instants.meme/2026/01/18/a-few-moments-later-sponge-bob-sfx-fun.mp3", "⏱️", "funny"),
            Sound("ACK", "https://cdn.instants.meme/2026/01/18/ack.mp3", "😠", "funny"),
            Sound("Romance", "https://cdn.instants.meme/2026/01/18/romanceeeeeeeeeeeeee.mp3", "💕", "funny"),
            Sound("Oh Brother Stinks", "https://cdn.instants.meme/2026/01/18/oh-brother-this-guy-stinks.mp3", "🤢", "funny"),
            Sound("GopGopGop", "https://cdn.instants.meme/2026/01/18/gopgopgop.mp3", "🗣️", "funny"),
            Sound("Im Bout To Eat", "https://cdn.instants.meme/2026/01/18/im-bout-to-eat-this-chick-fil-a.mp3", "🍗", "funny"),
            Sound("Aaaa Lutador", "https://cdn.instants.meme/2026/01/18/aaaaaaaaaaaaaaaaaaaa-e-lutador.mp3", "🤼", "funny"),
            Sound("Shocked Sound", "https://cdn.instants.meme/2026/01/18/shocked-sound.mp3", "😲", "effects"),
            Sound("Metal Pipe Falling", "https://cdn.instants.meme/2026/01/18/jixaw-metal-pipe-falling-sound.mp3", "🪠", "effects"),
            Sound("Apple Pay Sound", "https://cdn.instants.meme/2026/01/19/apple-pay-sound.mp3", "🍎", "effects"),
            Sound("Slap Hard", "https://cdn.instants.meme/2026/01/18/slap-hard.mp3", "👋", "effects"),
            Sound("Fahhhhhhhh Earrape", "https://cdn.instants.meme/2026/01/18/fahhhhhhhh-earrape.mp3", "🔊", "effects"),
            Sound("Fahhhhhhhhhh", "https://cdn.instants.meme/2026/01/18/fahhhhhhhhhhhhhhh.mp3", "🔊", "effects"),
            Sound("Skachai Maks", "https://cdn.instants.meme/2026/01/19/skachai-maks.mp3", "🎵", "effects"),
            Sound("Zvuk Fotoapparata", "https://cdn.instants.meme/2026/01/18/zvuk-fotoapparata.mp3", "📷", "effects"),
            Sound("Undertaker Bell", "https://cdn.instants.meme/2026/01/18/the-undertaker-bell.mp3", "🔔", "effects"),
            Sound("Skeleton Banging", "https://cdn.instants.meme/2026/01/18/skeleton-banging.mp3", "💀", "effects"),
            Sound("Whip", "https://cdn.instants.meme/2026/01/18/whip.mp3", "🪢", "effects"),
            Sound("Enrique", "https://cdn.instants.meme/2026/01/18/enrique.mp3", "🎵", "effects"),
            Sound("Running Sound", "https://cdn.instants.meme/2026/01/19/running-sound.mp3", "🏃", "effects"),
            Sound("Bad to the Bone", "https://cdn.instants.meme/2026/01/18/bad-to-the-bone-meme.mp3", "🎸", "effects"),
            Sound("Skeleton Shield", "https://cdn.instants.meme/2026/01/18/skeleton-with-shield.mp3", "🛡️", "effects")
        )
    }

    private fun setupRecyclerView() {
        adapter = SoundAdapter(allSounds) { sound, position ->
            playSound(sound, position)
        }
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = adapter
        updateSoundCount()
    }

    private fun setupCategoryTabs() {
        val tabs = listOf(
            binding.tabAll,
            binding.tabClassic,
            binding.tabViral,
            binding.tabGaming,
            binding.tabAnime,
            binding.tabFunny,
            binding.tabEffects
        )

        tabs.forEach { tab ->
            tab.setOnClickListener { view ->
                tabs.forEach { it.isSelected = false }
                view.isSelected = true
                
                currentCategory = when (view.id) {
                    R.id.tab_all -> "all"
                    R.id.tab_classic -> "classic"
                    R.id.tab_viral -> "viral"
                    R.id.tab_gaming -> "gaming"
                    R.id.tab_anime -> "anime"
                    R.id.tab_funny -> "funny"
                    R.id.tab_effects -> "effects"
                    else -> "all"
                }
                filterSounds()
            }
        }
    }

    private fun setupClickListeners() {
        binding.fabStop.setOnClickListener {
            stopSound()
        }
    }

    private fun filterSounds() {
        val filtered = if (currentCategory == "all") {
            allSounds
        } else {
            allSounds.filter { it.category == currentCategory }
        }
        adapter.submitList(filtered)
        updateSoundCount()
    }

    private fun playSound(sound: Sound, position: Int) {
        stopSound()
        
        vibrate()
        
        try {
            mediaPlayer = MediaPlayer().apply {
                setDataSource(sound.url)
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
                setOnCompletionListener {
                    adapter.notifyItemChanged(position)
                    currentPlayingPosition = -1
                }
                setOnErrorListener { _, _, _ ->
                    Toast.makeText(this@MainActivity, "Error playing sound", Toast.LENGTH_SHORT).show()
                    true
                }
            }
            currentPlayingPosition = position
            adapter.notifyItemChanged(position)
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopSound() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
        mediaPlayer = null
        
        if (currentPlayingPosition != -1) {
            adapter.notifyItemChanged(currentPlayingPosition)
            currentPlayingPosition = -1
        }
    }

    private fun vibrate() {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        }
    }

    private fun updateSoundCount() {
        val count = adapter.currentList.size
        binding.soundCount.text = "$count SOUNDS"
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSound()
    }

    override fun onPause() {
        super.onPause()
        stopSound()
    }
}

data class Sound(
    val name: String,
    val url: String,
    val icon: String,
    val category: String
)
