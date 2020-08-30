package developer.jimlyas.secand

import android.content.Context
import com.nekolaboratory.EmulatorDetector
import com.scottyab.rootbeer.RootBeer
import developer.jimlyas.secand.Secand.SecurityCheck
import developer.jimlyas.secand.Secand.SecurityCheck.EMULATOR
import developer.jimlyas.secand.Secand.SecurityCheck.ROOTED
import developer.jimlyas.secand.Secand.SecurityCheck.SAFE

/**
 * Class for checking device integrity
 *
 * @author Jimly A.
 * @since 30-Aug-20.
 * @property SecurityCheck Device state
 */

class Secand {

    enum class SecurityCheck { EMULATOR, ROOTED, SAFE }

    companion object {

        /**
         * Method to check device
         * @param ctx context
         * @return is device rooted or emulator or safe
         */
        fun check(ctx: Context): SecurityCheck {
            return when {
                isEmulator(ctx) -> EMULATOR
                isRooted(ctx) -> ROOTED
                else -> SAFE
            }
        }

        /**
         * @param ctx context
         * @return is device an emulator
         */
        private fun isEmulator(ctx: Context): Boolean {
            return try {
                EmulatorDetector.isEmulator(ctx)
            } catch (t: Throwable) {
                false
            }
        }

        /**
         * @param ctx context
         * @return is device rooted
         */
        private fun isRooted(ctx: Context): Boolean {
            return RootBeer(ctx).isRooted
        }
    }
}