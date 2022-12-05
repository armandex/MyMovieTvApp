package aguinaga.armando.mymovietvapp.utils

object Constantes  {

    var misprefsseguridad = "Misprefsseguridad"
    var misprefsconfiguracion = "Misprefsconfi"
    val myBearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OTRhOTVhMjVjNzBjOWNlNmU5NzY4YmU2MDQ1OTk1NiIsInN1YiI6IjYyZDMwY2UyZGQ3MzFiMDA1NGViYjNjNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lEeARV0Ke3YNpzrzYo3Jeg2H-nC1TXAdcQ-l3K1w1O8"

    val username = "username"
    const val PASSWORD_PATTERN =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}].:;',?/*~$^+=<>]).{8,19}$"
    /***/
    /*
            ^                 # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=.*[@#$%^&+=])  # a special character must occur at least once
            (?=\S+$)          # no whitespace allowed in the entire string
            .{8,}             # anything, at least eight places though
            $                 # end-of-string

    */
    /***/
    const val moviesFragment = "moviesFragment"
    const val moviesDetailFragment = "moviesDetailFragment"
}